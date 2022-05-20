package com.fwcorp.fwautogestao.controllers;

import com.fwcorp.fwautogestao.services.UsuarioService;
import com.fwcorp.fwautogestao.util.RespostaServidor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
