package com.fwcorp.fwautogestao.services;

import com.fwcorp.fwautogestao.dto.registro.RegistroUsuarioDTO;
import com.fwcorp.fwautogestao.entities.Maintainer;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.entities.Usuario;
import com.fwcorp.fwautogestao.enums.Cargos;
import com.fwcorp.fwautogestao.exceptions.CargoIlegalException;
import com.fwcorp.fwautogestao.exceptions.EmailEmUsoException;
import com.fwcorp.fwautogestao.exceptions.EntityNotFoundException;
import com.fwcorp.fwautogestao.exceptions.NomeInvalidoException;
import com.fwcorp.fwautogestao.repositories.MaintainerRepository;
import com.fwcorp.fwautogestao.repositories.UsuarioRepository;
import com.fwcorp.fwautogestao.util.BindingResultUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MaintainerService {

    private final MaintainerRepository maintainerRepository;

    public Maintainer obterMaintainer(String id) {
        return maintainerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
    }

    public Page<Maintainer> listarMaintainers(Pageable pageable) {
        return maintainerRepository.findAll(pageable);
    }

}
