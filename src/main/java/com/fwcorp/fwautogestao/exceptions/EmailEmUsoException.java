package com.fwcorp.fwautogestao.exceptions;

public class EmailEmUsoException extends RuntimeException{

    public EmailEmUsoException(String mensagem){
        super(mensagem);
    }
}
