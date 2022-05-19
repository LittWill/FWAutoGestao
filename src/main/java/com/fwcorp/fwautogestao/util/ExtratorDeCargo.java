package com.fwcorp.fwautogestao.util;

import com.fwcorp.fwautogestao.entities.Cargo;

public class ExtratorDeCargo {

    public static Cargo extrairCargo(Object objeto){
        String classeNome = objeto.getClass().getSimpleName();
        return new Cargo(classeNome);
    }
}
