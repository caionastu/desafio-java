package com.caionastu.desafiojava.fazenda.dominio;

import lombok.Getter;
import lombok.NonNull;

public enum SiglaEstados {
    AC("AC"),
    AL("AL"),
    AP("AP"),
    AM("AM"),
    BA("BA"),
    CE("CE"),
    ES("ES"),
    GO("GO"),
    MA("MA"),
    MT("MT"),
    MS("MS"),
    MG("MG"),
    PA("PA"),
    PB("PB"),
    PR("PR"),
    PE("PE"),
    PI("PI"),
    RJ("RJ"),
    RN("RN"),
    RS("RS"),
    RO("RO"),
    RR("RR"),
    SC("SC"),
    SP("SP"),
    SE("SE"),
    TO("TO");

    @Getter
    private final String sigla;

    SiglaEstados(@NonNull String sigla) {
        this.sigla = sigla;
    }
}
