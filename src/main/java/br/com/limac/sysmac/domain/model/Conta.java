package br.com.limac.sysmac.domain.model;

import br.com.limac.sysmac.domain.exception.NegocioException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_CONTA")
@SequenceGenerator(name = "conta_seq", sequenceName = "conta_seq", initialValue = 1, allocationSize = 1)
public class Conta {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_seq")
    private Long id;

    @NotNull
    @Column(nullable = false, length = 20, unique = true)
    private String agencia;

    @NotNull
    @Column(nullable = false, unique = true)
    private Long conta;

    @NotNull
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Banco banco;

    @Column(columnDefinition = "boolean default true")
    private boolean estaAtivo;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dataCriacao;

    @Column
    private OffsetDateTime dataAtualizacao;


    public void depositar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public void saque(BigDecimal valor) {
        if (valor.compareTo(saldo) > 1)
            throw new NegocioException(
                    String.format("Valor retirado maior que saldo da conta saldo: %0.4f, valor retirado %0.4f",
                            this.saldo, valor));

        this.saldo = this.saldo.add(valor);
    }

    @PrePersist
    public void onInsert() {
        this.dataCriacao = OffsetDateTime.now();
        this.dataAtualizacao = this.dataCriacao;
    }

    @PreUpdate
    public void onUpdate() {
        this.dataAtualizacao = OffsetDateTime.now();
    }
}
