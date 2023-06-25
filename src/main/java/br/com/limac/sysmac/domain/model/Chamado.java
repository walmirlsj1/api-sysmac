package br.com.limac.sysmac.domain.model;


import br.com.limac.sysmac.domain.model.enums.EChamadoStatus;
import br.com.limac.sysmac.domain.model.enums.EStatusNota;
import jakarta.persistence.*;
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
@Table(name = "TB_CHAMADO")
@SequenceGenerator(name = "chamado_seq", sequenceName = "chamado_seq", initialValue = 1, allocationSize = 1)
public class Chamado {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chamado_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany
    @JoinColumn(name = "conta_receber_id")
    private Set<Duplicata> duplicata = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status_chamado")
    private EChamadoStatus statusChamado;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento")
    private EStatusNota statusPagamento;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "solicitante")
    private String solicitante;

    @NotNull
    @Column(name = "valor_bruto_servico")
    private BigDecimal valorBrutoServico;

    private BigDecimal desconto;

    @NotNull
    private BigDecimal valorAReceber;

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
