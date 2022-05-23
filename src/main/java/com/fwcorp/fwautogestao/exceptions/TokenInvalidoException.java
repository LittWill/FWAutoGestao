package com.fwcorp.fwautogestao.exceptions;

public class TokenInvalidoException extends RuntimeException{
    public TokenInvalidoException(String mensagem){
        super(mensagem);
    }
}
