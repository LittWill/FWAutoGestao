package com.fwcorp.fwautogestao.controllers;

import com.fwcorp.fwautogestao.entities.GestorMaintainer;
import com.fwcorp.fwautogestao.entities.Maintainer;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.entities.UsuarioGestor;
import com.fwcorp.fwautogestao.enums.Cargos;
import com.fwcorp.fwautogestao.services.CargoService;
import com.fwcorp.fwautogestao.services.SecurityInfo;
import com.fwcorp.fwautogestao.services.TokenRegistroService;
import com.fwcorp.fwautogestao.util.GeradorRespostaServidor;
import com.fwcorp.fwautogestao.util.RespostaServidor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tokens")
public class TokenController {

    private final TokenRegistroService tokenRegistroService;

    private final CargoService cargoService;

    public TokenController(TokenRegistroService tokenRegistroService, CargoService cargoService) {
        this.tokenRegistroService = tokenRegistroService;
        this.cargoService = cargoService;
    }

    @PostMapping("/gerar/gestormaintainer")
    public ResponseEntity<RespostaServidor> gerarTokenParaGestorMaintainer() {
        TokenRegistro tokenParaGestorMaintainer = new TokenRegistro((UsuarioGestor) SecurityInfo.obterUsuarioLogado(), cargoService.obterCargo(Cargos.GESTORMAINTAINER.getNomeCargo()));
        tokenParaGestorMaintainer = tokenRegistroService.salvarToken(tokenParaGestorMaintainer);
        return GeradorRespostaServidor.gerarRespostaServidorStatusOk(tokenParaGestorMaintainer.getToken());
    }

    @PostMapping("/gerar/maintainer")
    public ResponseEntity<RespostaServidor> gerarTokenParaMaintainer() {
        TokenRegistro tokenParaMaintainer = new TokenRegistro((UsuarioGestor) SecurityInfo.obterUsuarioLogado(), cargoService.obterCargo(Cargos.MAINTAINER.getNomeCargo()));
        tokenParaMaintainer = tokenRegistroService.salvarToken(tokenParaMaintainer);
        return GeradorRespostaServidor.gerarRespostaServidorStatusOk(tokenParaMaintainer.getToken());
    }

    @GetMapping("/gestormaintainer")
    public ResponseEntity<RespostaServidor> listarGestoresMaintainer(@PageableDefault Pageable pageable) {
        return GeradorRespostaServidor.gerarRespostaServidorStatusOk(tokenRegistroService.listarTokensGestorMaintainer(pageable));
    }

    @GetMapping("/maintainer")
    public ResponseEntity<RespostaServidor> listarMaintainers(@PageableDefault Pageable pageable) {
        return GeradorRespostaServidor.gerarRespostaServidorStatusOk(tokenRegistroService.listarTokensMaintainers(pageable));
    }
}
