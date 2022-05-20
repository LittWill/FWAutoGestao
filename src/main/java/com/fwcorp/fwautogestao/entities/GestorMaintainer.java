package com.fwcorp.fwautogestao.entities;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class GestorMaintainer extends UsuarioGestorComum{

    public GestorMaintainer (TokenRegistro tokenRegistro, String primeiroNome, String ultimoNome, String urlImagem, String email, String senha){
        super(tokenRegistro, primeiroNome, ultimoNome, urlImagem, email, senha);
    }
}
