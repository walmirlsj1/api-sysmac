package br.com.limac.sysmac.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_PLANO_CONTA")
@SequenceGenerator(name = "plano_conta_seq", sequenceName = "plano_conta_seq", initialValue = 1, allocationSize = 1)
public class PlanoConta {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plano_conta_seq")
    private Long id;

    @Column(length = 28, unique = true)
    private String classificacao;

    @Column(length = 100)
    private String descricao;

    @Column
    private Boolean analitico;

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
