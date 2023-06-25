package br.com.limac.sysmac.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_PRODUTO")
@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", initialValue = 1, allocationSize = 1)
public class Produto {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    private Long id;

    @Column
    private String descricao;

    @Column
    private int qtd_estoque;

    @Column
    private double custo;

    @Column
    private double lucro;

    @Column(name = "preco_venda")
    private double preco;

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
