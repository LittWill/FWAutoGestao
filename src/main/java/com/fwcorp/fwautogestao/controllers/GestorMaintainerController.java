package com.fwcorp.fwautogestao.controllers;

import com.fwcorp.fwautogestao.dto.registro.RegistroUsuarioDTO;
import com.fwcorp.fwautogestao.entities.GestorMaintainer;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.enums.Cargos;
import com.fwcorp.fwautogestao.services.TokenRegistroService;
import com.fwcorp.fwautogestao.services.UsuarioService;
import com.fwcorp.fwautogestao.util.GeradorRespostaServidor;
import com.fwcorp.fwautogestao.util.RespostaServidor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("gestormaintainer")
@Api(tags = "Gestor Maintainer Endpoints")
public class GestorMaintainerController {

    private final UsuarioService usuarioService;

    private final TokenRegistroService tokenRegistroService;

    @ApiOperation(value = "Salva um novo Gestor Maintainer", tags = "Acesso Livre")
    @PostMapping
    public ResponseEntity<RespostaServidor> salvarGestorMaintainer(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO, BindingResult bindingResult){

        TokenRegistro tokenRegistro = tokenRegistroService.buscarToken(registroUsuarioDTO.getTokenRegistro());

        List<String> errosDoCadastro = usuarioService.procurarErros(bindingResult, registroUsuarioDTO, Cargos.GESTORMAINTAINER, tokenRegistro);

        if (!errosDoCadastro.isEmpty()){
            return GeradorRespostaServidor.gerarRespostaServidorStatusBadRequest(errosDoCadastro, "Não foi possível concluir o cadastro!");
        }

        tokenRegistro.setUtilizado(true);
        usuarioService.salvarUsuario(new GestorMaintainer(tokenRegistro, registroUsuarioDTO.getPrimeiroNome(), registroUsuarioDTO.getUltimoNome(), "image.jpeg", registroUsuarioDTO.getSenha()));

        return GeradorRespostaServidor.gerarRespostaServidorStatusCreated("O usuário foi criado com sucesso!");


    }

}
