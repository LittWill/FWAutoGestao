package com.fwcorp.fwautogestao.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public abstract class UsuarioGestorComum extends UsuarioGestor{

    @ManyToOne(cascade = CascadeType.REFRESH)
    private TokenRegistro tokenRegistro;

    public UsuarioGestorComum(TokenRegistro tokenRegistro, String primeiroNome, String ultimoNome, String urlImagem, String senha){
        super(primeiroNome, ultimoNome, urlImagem, senha);
        this.tokenRegistro = tokenRegistro;
    }
}
