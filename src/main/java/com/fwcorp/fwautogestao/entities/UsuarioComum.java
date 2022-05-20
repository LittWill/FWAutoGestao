package com.fwcorp.fwautogestao.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter
@NoArgsConstructor
public abstract class UsuarioComum extends Usuario{

   @ManyToOne
   private TokenRegistro tokenRegistro;

   public UsuarioComum(TokenRegistro tokenRegistro, String primeiroNome, String ultimoNome, String urlImagem, String email, String senha){
      super(primeiroNome, ultimoNome, urlImagem, email, senha);
      this.tokenRegistro = tokenRegistro;
   }

}
