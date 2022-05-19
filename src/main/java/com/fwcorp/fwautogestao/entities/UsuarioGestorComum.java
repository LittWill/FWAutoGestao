package com.fwcorp.fwautogestao.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class UsuarioGestorComum extends UsuarioGestor{

    @ManyToOne
    private TokenRegistro tokenRegistro;

    public UsuarioGestorComum(TokenRegistro tokenRegistro, String primeiroNome, String ultimoNome, String urlImagem, String email, String senha){
        super(primeiroNome, ultimoNome, urlImagem, email, senha);
        this.tokenRegistro = tokenRegistro;
    }
}
