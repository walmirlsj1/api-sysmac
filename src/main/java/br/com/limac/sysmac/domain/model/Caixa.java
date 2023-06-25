package br.com.limac.sysmac.domain.model;

import br.com.limac.sysmac.domain.model.enums.ETipoCaixa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_CAIXA")
@SequenceGenerator(name = "caixa_seq", sequenceName = "caixa_seq", initialValue = 1, allocationSize = 1)
public class Caixa {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caixa_seq")
    private Long id;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "tipo_registro_caixa")
    private ETipoCaixa tipoRegistroCaixa;

    @NotBlank
    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "plano_conta_id", nullable = false)
    private PlanoConta planoConta;

    @NotNull
    private BigDecimal valor;

    @OneToMany(mappedBy = "caixa", fetch = FetchType.LAZY)
    private Set<DuplicataParcela> duplicataParcela = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dataCriacao;

    @Column
    private OffsetDateTime dataAtualizacao;

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
