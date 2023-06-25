package br.com.limac.sysmac.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_BANCO")
@SequenceGenerator(name = "banco_seq", sequenceName = "banco_seq", initialValue = 1, allocationSize = 1)
public class Banco {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "banco_seq")
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 120, unique = true)
    private String nome;

    @OneToMany(mappedBy = "banco")
    @JsonIgnore
    private List<Conta> contas = new ArrayList<>();

    @Column(columnDefinition = "boolean default true")
    private boolean estaAtivo;

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
