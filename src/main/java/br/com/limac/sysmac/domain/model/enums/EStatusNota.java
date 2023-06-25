package br.com.limac.sysmac.domain.model.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum EStatusNota {
    PENDENTE(0, "Pendente"),
    FATURADO(1, "Faturado"),
    CANCELADO(2, "Cancelado"),
    CONTRATO(3, "Contrato");

    //    Faturado(7, "Faturado");
    private int id;
    private String name;

    EStatusNota(int id, String name) {
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

    public static EStatusNota getById(int id) {
        for (EStatusNota e : values()) {
            if (e.id == id) return e;
        }
        return PENDENTE;
    }
}
