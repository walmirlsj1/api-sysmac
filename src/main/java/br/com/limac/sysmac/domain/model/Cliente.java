package br.com.limac.sysmac.domain.model;

import br.com.limac.sysmac.domain.model.enums.EEstadoCivil;
import br.com.limac.sysmac.domain.model.enums.ESexo;
import br.com.limac.sysmac.domain.model.enums.ETipoPessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_CLIENTE")
@SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", initialValue = 1, allocationSize = 1)
public class Cliente {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    private Long id;

    @NotBlank(message = "Campo nome não pode estar vazio")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "fantasia")
    private String fantasia;

    @NotBlank(message = "Campo CPF/CNPJ não pode estar vazio")
    @Column(name = "cpf_cnpj", unique = true)
    private String cpfCnpj;

    @Column(name = "rg_ie", unique = true)
    private String rgIe;

    @Column(name = "sexo", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ESexo sexo;

    @Column(name = "estado_civil", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private EEstadoCivil estadoCivil;

    @Column(name = "email")
    private String email;

    @Column(name = "celular")
    private String celular;

    @Column
    private OffsetDateTime dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_empresa", nullable = false, updatable = false)
    private ETipoPessoa tipoEmpresa;

    @Column(columnDefinition = "boolean default true")
    private boolean estaAtivo = true;

//    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = CascadeType.ALL)
//    private List<Conjuge> conjuges = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = CascadeType.ALL)
    @NotEmpty(message = "Deve possuir pelo menos um endereço cadastrado")
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = CascadeType.ALL)
    @NotEmpty(message = "Deve possuir pelo menos uma informação de contato")
    private List<Contato> contatos = new ArrayList<>();


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
