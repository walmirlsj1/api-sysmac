package br.com.limac.sysmac.domain.model.enums;

import java.util.Arrays;
import java.util.List;

public enum EStatusProjeto {

    PENDENTE("Pendente"),
    CONSTRUCAO_INICIAL("Levantamento"),
    AGUARDANDO_DOCUMENTOS("Aguardando Documentação"),
    PROTOCOLADO("Protocolado"),
    CORRECAO_NECESSARIA("Em Correção"),
    APROVADO("Aprovado"),
    CONCLUIDO("Concluido"),
    CANCELADO("Cancelado");

    private String descricao;

    EStatusProjeto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
