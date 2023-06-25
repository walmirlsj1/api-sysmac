package br.com.limac.sysmac.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_ENDERECO")
@SequenceGenerator(name = "endereco_seq", sequenceName = "endereco_seq", initialValue = 1, allocationSize = 1)
public class Endereco {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq")
    private Long id;

    @Column(name = "cep")
    private long cep;

    @Column(name = "rua")
    private String rua;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "numero")
    private long numero;

    @Column(name = "complemento")
    private String complemento;

    @NotNull(message = "Campo nome nao pode esta vazio")
    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

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
