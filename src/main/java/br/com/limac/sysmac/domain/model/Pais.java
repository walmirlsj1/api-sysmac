package br.com.limac.sysmac.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_PAIS")
@SequenceGenerator(name = "pais_seq", sequenceName = "pais_seq", initialValue = 1, allocationSize = 1)
public class Pais {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_seq")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sigla")
    private String sigla;

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
