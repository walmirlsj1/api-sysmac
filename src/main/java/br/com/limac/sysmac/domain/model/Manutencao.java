package br.com.limac.sysmac.domain.model;

import br.com.limac.sysmac.domain.model.enums.EStatusEquipamento;
import br.com.limac.sysmac.domain.model.enums.EStatusNota;
import br.com.limac.sysmac.security.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_MANUTENCAO")
@SequenceGenerator(name = "manutencao_seq", sequenceName = "manutencao_seq", initialValue = 1, allocationSize = 1)
public class Manutencao {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manutencao_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipamento_id", nullable = false)
    private Equipamento equipamento;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_equipamento")
    private EStatusEquipamento statusEquipamento;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_nota")
    private EStatusNota statusNota;

    @OneToOne
    private ContaReceber contaReceber;

    @NotNull
    private String problemaRelatado;
    private String solucao;
    private String observacao;
    private Double valorTotal;

    //    @ JsonIgnore
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    //    @ JsonIgnore
    @ManyToOne
    @JoinColumn(name = "updated_by", nullable = false)
    private User updatedBy;

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
