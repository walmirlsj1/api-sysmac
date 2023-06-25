package br.com.limac.sysmac.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_CIDADE")
@SequenceGenerator(name = "cidade_seq", sequenceName = "cidade_seq", initialValue = 1, allocationSize = 1)
public class Cidade {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade_seq")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;


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
