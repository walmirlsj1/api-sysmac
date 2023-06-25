package br.com.limac.sysmac.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EChamadoStatus {
    PENDENTE(0, "Pendente"),
    PROCESSO(1, "Processo"),
    CONCLUIDO(2, "Concluído"),
    RETORNO(3, "Retorno"),
    CANCELADA(4, "Cancelada"); //era 5

//    FATURADO(4, "Faturado"),
//    CONTRATO(6, "Contrato");


    private int id;
    private String name;

    EChamadoStatus(int id, String name) {
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

    public static EChamadoStatus getById(int id) {
        for (EChamadoStatus e : values()) {
            if (e.id == id) return e;
        }
        return PENDENTE;
    }
}
