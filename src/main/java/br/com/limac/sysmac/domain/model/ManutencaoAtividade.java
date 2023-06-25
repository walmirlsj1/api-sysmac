package br.com.limac.sysmac.domain.model;

import br.com.limac.sysmac.security.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_MANUTENCAO_ATIVIDADE")
@SequenceGenerator(name = "manutencao_ativ_seq", sequenceName = "manutencao_ativ_seq", initialValue = 1, allocationSize = 1)
public class ManutencaoAtividade {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manutencao_ativ_seq")
    private Long id;

    @JsonIgnoreProperties("atividade")
    @ManyToOne
    @JoinColumn(name = "manutencao_id", nullable = false)
    private Manutencao manutencao;

    @Column(name = "descricao")
    private String descricao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

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
