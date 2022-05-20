package com.fwcorp.fwautogestao.enums;

public enum Cargos {
    ADMNISTRADOR("Administrador"), GESTORMAINTAINER("GestorMaintainer"), MAINTAINER("Maintainer");

    Cargos(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    private final String nomeCargo;

    public String getNomeCargo(){
        return nomeCargo;
    }
}
