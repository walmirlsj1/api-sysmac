package br.com.limac.sysmac.domain.model;

import br.com.limac.sysmac.domain.model.enums.EFormaPagamento;
import br.com.limac.sysmac.security.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_CONTA_RECEBER")
@SequenceGenerator(name = "conta_receber_seq", sequenceName = "conta_receber_seq", initialValue = 1, allocationSize = 1)
public class ContaReceber {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_receber_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private EFormaPagamento formaPagamento;

    @NotNull
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    @JsonIgnore
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by", nullable = false)
    @JsonIgnore
    private User updatedBy;

//    @OneToMany(mappedBy = "contaReceber")
    /**
     * mappedBy significa que este lado do relacionamento não tem um forengkey de parcela,
     * e também faz referencia a variavel utilizada na classe ContaReceberParcela.
     */
//    private List<Titulo> parcelas;

    @OneToOne(mappedBy = "contaReceber")
    private Manutencao manutencao;

    @OneToOne(mappedBy = "contaReceber")
    private Chamado chamado;


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
