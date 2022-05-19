package com.fwcorp.fwautogestao.entities;

import antlr.Token;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class UsuarioComum extends Usuario{

   @ManyToOne
   private TokenRegistro tokenRegistro;

   public UsuarioComum(TokenRegistro tokenRegistro, String primeiroNome, String ultimoNome, String urlImagem, String email, String senha){
      super(primeiroNome, ultimoNome, urlImagem, email, senha);
      this.tokenRegistro = tokenRegistro;
   }

}
