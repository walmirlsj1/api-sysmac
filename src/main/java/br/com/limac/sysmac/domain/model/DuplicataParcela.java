package br.com.limac.sysmac.domain.model;

import br.com.limac.sysmac.domain.model.enums.EEspeciePagamento;
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
@Table(name = "TB_DUPLICATA_PARCELA")
@SequenceGenerator(name = "duplicata_parcela_seq", sequenceName = "duplicata_parcela_seq", initialValue = 1, allocationSize = 1)
public class DuplicataParcela {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "duplicata_parcela_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private EEspeciePagamento especiePagamento;

    @NotNull
    private BigDecimal valor_a_receber;

    @NotNull
    private BigDecimal valor_recebido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "duplicata_id")
    private Duplicata duplicata;

    @ManyToOne
    @JoinColumn(name = "caixa_id")
    private Caixa caixa;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    @JsonIgnore
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by", nullable = false)
    @JsonIgnore
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
