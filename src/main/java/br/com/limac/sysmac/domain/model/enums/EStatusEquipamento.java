package br.com.limac.sysmac.domain.model.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum EStatusEquipamento {
    PENDENTE(0, "Pendente"),
    PENDENTE_POR_PECA(1, "Pendente por peça"),
    AGUARDANDO_APROVACAO(2, "Aguardando Aprovação"),
    MANUTENCAO(3, "Manutenção"),
    PRONTO(4, "Pronto"),
    ENTREGUE(5, "Entregue");
    private int id;
    private String name;

    EStatusEquipamento(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @JsonValue
    /** detalhe pra enviar via JSON somente o id, ou código ordinal do Enum */
    public int getId() {
        return id;
    }

    public static EStatusEquipamento getById(int id) {
        for (EStatusEquipamento e : values()) {
            if (e.id == id) return e;
        }
        return PENDENTE;
    }
}
