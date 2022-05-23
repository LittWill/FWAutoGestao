package com.fwcorp.fwautogestao.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class Maintainer extends UsuarioComum{

    public Maintainer (TokenRegistro tokenRegistro, String primeiroNome, String ultimoNome, String urlImagem, String senha){
        super(tokenRegistro, primeiroNome, ultimoNome, urlImagem, senha);
    }

}
