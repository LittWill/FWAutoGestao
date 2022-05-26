package com.fwcorp.fwautogestao.controllers;

import com.fwcorp.fwautogestao.dto.registro.RegistroUsuarioDTO;
import com.fwcorp.fwautogestao.dto.view.ViewUsuarioComumDTO;
import com.fwcorp.fwautogestao.entities.Maintainer;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.enums.Cargos;
import com.fwcorp.fwautogestao.mappers.UsuarioMapper;
import com.fwcorp.fwautogestao.services.MaintainerService;
import com.fwcorp.fwautogestao.services.TokenRegistroService;
import com.fwcorp.fwautogestao.services.UsuarioService;
import com.fwcorp.fwautogestao.util.GeradorRespostaServidor;
import com.fwcorp.fwautogestao.util.RespostaServidor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("maintainer")
@Api(tags = "Maintainer Endpoints")
public class MaintainerController {

    private final UsuarioService usuarioService;

    private final MaintainerService maintainerService;

    private final TokenRegistroService tokenRegistroService;

    @ApiOperation(value = "Salva um novo Maintainer", tags = "Acesso Livre")
    @PostMapping
    public ResponseEntity<RespostaServidor> salvarMaintainer(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO, BindingResult bindingResult){

        TokenRegistro tokenRegistro = tokenRegistroService.buscarToken(registroUsuarioDTO.getTokenRegistro());

        List<String> errosDoCadastro = usuarioService.procurarErros(bindingResult, registroUsuarioDTO, Cargos.GESTORMAINTAINER, tokenRegistro);

        if (!errosDoCadastro.isEmpty()){
            return GeradorRespostaServidor.gerarRespostaServidorStatusBadRequest(errosDoCadastro, "Não foi possível concluir o cadastro!");
        }

        tokenRegistro.setUtilizado(true);
        usuarioService.salvarUsuario(new Maintainer(tokenRegistro, registroUsuarioDTO.getPrimeiroNome(), registroUsuarioDTO.getUltimoNome(), "image.jpeg", registroUsuarioDTO.getSenha()));

        return GeradorRespostaServidor.gerarRespostaServidorStatusCreated("O usuário foi criado com sucesso!");


    }

    @GetMapping
    public ResponseEntity<RespostaServidor> listarMaintainers(@PageableDefault Pageable pageable){
        Page<ViewUsuarioComumDTO> maintainers = maintainerService.listarMaintainers(pageable).map(UsuarioMapper::entityToViewUsuarioComumDTO);
        return GeradorRespostaServidor.gerarRespostaServidorStatusOk(maintainers);


    }


}
