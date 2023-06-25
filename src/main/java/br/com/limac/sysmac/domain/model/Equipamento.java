package br.com.limac.sysmac.domain.model;


import br.com.limac.sysmac.domain.model.enums.EEquipamentoTipo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_EQUIPAMENTO")
@SequenceGenerator(name = "equipamento_seq", sequenceName = "equipamento_seq", initialValue = 1, allocationSize = 1)
public class Equipamento {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipamento_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private String observacao;

    private String cor;

    @Column(name = "num_serie")
    private String numSerie;

    @Enumerated(EnumType.STRING)
    private EEquipamentoTipo tipoEquipamento;

    private String responsavel;

    @ManyToOne
    @JoinColumn(name = "modelo_id", nullable = false)
    private Modelo modelo;

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
