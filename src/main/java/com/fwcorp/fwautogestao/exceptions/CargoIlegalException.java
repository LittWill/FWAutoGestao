package com.fwcorp.fwautogestao.exceptions;

public class CargoIlegalException extends RuntimeException{

    public CargoIlegalException(String mensagem){
        super(mensagem);
    }
}
