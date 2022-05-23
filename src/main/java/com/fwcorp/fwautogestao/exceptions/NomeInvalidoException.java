package com.fwcorp.fwautogestao.exceptions;

public class NomeInvalidoException extends RuntimeException{
    public NomeInvalidoException(String mensagem){
        super(mensagem);
    }
}
