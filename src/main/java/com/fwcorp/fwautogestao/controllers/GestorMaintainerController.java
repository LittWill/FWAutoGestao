package com.fwcorp.fwautogestao.controllers;

import com.fwcorp.fwautogestao.dto.registro.RegistroUsuarioDTO;
import com.fwcorp.fwautogestao.entities.GestorMaintainer;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.enums.Cargos;
import com.fwcorp.fwautogestao.services.TokenRegistroService;
import com.fwcorp.fwautogestao.services.UsuarioService;
import com.fwcorp.fwautogestao.util.GeradorRespostaServidor;
import com.fwcorp.fwautogestao.util.RespostaServidor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gestormaintainer")
public class GestorMaintainerController {

    private final UsuarioService usuarioService;

    private final TokenRegistroService tokenRegistroService;

    public GestorMaintainerController(UsuarioService usuarioService, TokenRegistroService tokenRegistroService) {
        this.usuarioService = usuarioService;
        this.tokenRegistroService = tokenRegistroService;
    }

    @PostMapping
    public ResponseEntity<RespostaServidor> salvarGestorMaintainer(@RequestBody RegistroUsuarioDTO registroUsuarioDTO){
        TokenRegistro tokenRegistro = tokenRegistroService.buscarToken(registroUsuarioDTO.getTokenRegistro());

        if (null == tokenRegistro){
            return GeradorRespostaServidor.gerarRespostaServidor(HttpStatus.NOT_FOUND, "Este token já foi utilizado!");
        }
        boolean cargoValido = tokenRegistro.getTokenCargo().getNome().equals(Cargos.GESTORMAINTAINER.getNomeCargo());

        if (!cargoValido){
            return GeradorRespostaServidor.gerarRespostaServidor(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível completar a operação!");
        }

        tokenRegistro.setUtilizado(true);
        usuarioService.salvarUsuario(new GestorMaintainer(tokenRegistro, registroUsuarioDTO.getPrimeiroNome(), registroUsuarioDTO.getUltimoNome(), "image.jpeg", registroUsuarioDTO.getEmail(), registroUsuarioDTO.getSenha()));


        return GeradorRespostaServidor.gerarRespostaServidorStatusCreated("O usuário foi criado com sucesso!");


    }

}
