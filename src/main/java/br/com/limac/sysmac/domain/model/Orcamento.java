package br.com.limac.sysmac.domain.model;

import br.com.limac.sysmac.domain.model.enums.ESituacaoOrcamento;
import br.com.limac.sysmac.domain.model.enums.EStatusOrcamento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_ORCAMENTO")
@SequenceGenerator(name = "orcamento_seq", sequenceName = "orcamento_seq", initialValue = 1, allocationSize = 1)
public class Orcamento {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orcamento_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "equipamento_id", nullable = true)
    private Equipamento equipamento;

    @Column
    private String solicitante;

    @Column
    private String problema_relatado;

    @Column
    private String observacao;

    @Column
    @Enumerated(EnumType.STRING)
    private ESituacaoOrcamento situacao;

    @Column
    @Enumerated(EnumType.STRING)
    private EStatusOrcamento status;

    @Column
    private String autorizadoBy;

    @Column(name = "autorizado_at")
    private String autorizadoAt;

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
