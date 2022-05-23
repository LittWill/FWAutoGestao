package com.fwcorp.fwautogestao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public abstract class UsuarioGestor extends Usuario{

    @OneToMany(mappedBy = "quemGerou")
    private List<TokenRegistro> tokensGerados;

    public UsuarioGestor(String primeiroNome, String ultimoNome, String urlImagem, String senha){
        super(primeiroNome, ultimoNome, urlImagem, senha);
    }




}
