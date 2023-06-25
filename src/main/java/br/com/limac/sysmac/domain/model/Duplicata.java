package br.com.limac.sysmac.domain.model;

import br.com.limac.sysmac.domain.model.enums.EFormaPagamento;
import br.com.limac.sysmac.security.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_DUPLICATA")
@SequenceGenerator(name = "duplicata_seq", sequenceName = "duplicata_seq", initialValue = 1, allocationSize = 1)
public class Duplicata {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "duplicata_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private EFormaPagamento formaPagamento;

    private Long qtdParcela;

    @NotNull
    private BigDecimal valorTotalAReceber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chamado_id")
    private Chamado chamado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manutencao_id")
    private Manutencao manutencao;

//    @ManyToOne
//    @JoinColumn(name = "conta_id", nullable = false)
//    private Conta conta;
//    @ManyToOne
//    @JoinColumn(name = "conta_receber_parent_id")
//    private ContaReceber contaReceber;

    @ManyToOne
    @JoinColumn(name = "plano_conta_id", nullable = false)
    private PlanoConta planoConta;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    @JsonIgnore
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by", nullable = false)
    @JsonIgnore
    private User updatedBy;
    /**
     * mappedBy significa que este lado do relacionamento não tem um forengkey de parcela,
     * e também faz referencia a variavel utilizada na classe ContaReceberParcela.
     */
//    @OneToMany(mappedBy = "contaReceber";
//    private List<Titulo> parcelas;


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
