package com.fwcorp.fwautogestao.services;

import com.fwcorp.fwautogestao.dto.registro.RegistroUsuarioDTO;
import com.fwcorp.fwautogestao.entities.TokenRegistro;
import com.fwcorp.fwautogestao.enums.Cargos;
import com.fwcorp.fwautogestao.exceptions.CargoIlegalException;
import com.fwcorp.fwautogestao.exceptions.EmailEmUsoException;
import com.fwcorp.fwautogestao.exceptions.EntityNotFoundException;
import com.fwcorp.fwautogestao.exceptions.NomeInvalidoException;
import com.fwcorp.fwautogestao.util.BindingResultUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fwcorp.fwautogestao.entities.Usuario;
import com.fwcorp.fwautogestao.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setEmail(this.gerarEmail(usuario.getPrimeiroNome(), usuario.getUltimoNome()));
        return usuarioRepository.save(usuario);
    }

    public Usuario obterUsuario(String id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
    }

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public void excluirUsuario(String id) {
        Usuario usuarioObtido = this.obterUsuario(id);
        usuarioRepository.delete(usuarioObtido);
    }

    private void verificarSeEmailEstaEmUso(String email) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new EmailEmUsoException(String.format("O email %s está em uso!", email));
        }
    }

    private void verificarNome(String primeiroNome, String ultimoNome) {
        String regex = "[^A-Za-z]";
        String primeiroNomeVerificado = primeiroNome.replaceAll(regex, "_");
        String ultimoNomeVerificado = ultimoNome.replaceAll(regex, "_");
        boolean primeiroEUltimoNomeInvalidos = primeiroNomeVerificado.contains("_")
                || ultimoNomeVerificado.contains("_");
        if (primeiroEUltimoNomeInvalidos) {
            throw new NomeInvalidoException("Primeiro ou último nome inválido!");
        }
    }

    private void verificarCargo(Cargos cargo, TokenRegistro tokenRegistro) {
        if (!cargo.getNomeCargo().equals(tokenRegistro.getTokenCargo().getNome())) {
            throw new CargoIlegalException("Esse token não pode ser utilizado neste cargo!");
        }
    }


    public List<String> procurarErros(BindingResult bindingResult, RegistroUsuarioDTO registroUsuarioDTO, Cargos cargo, TokenRegistro tokenRegistro) {
        List<String> erros = new ArrayList<>(BindingResultUtils.extrairErrosDosCampos(bindingResult));

        try {
            this.verificarNome(registroUsuarioDTO.getPrimeiroNome(), registroUsuarioDTO.getUltimoNome());
        } catch (NomeInvalidoException ex) {
            erros.add(ex.getMessage());
        }

        try {
            this.verificarCargo(cargo, tokenRegistro);
        } catch (CargoIlegalException ex) {
            erros.add(ex.getMessage());
        }

        return erros;

    }

    public String gerarEmail(String primeiroNome, String ultimoNome) {
        int num = 1;
        String email = buildarEmail(primeiroNome, ultimoNome, num);

        boolean emailEstaEmUso = true;

        do {
            try {
                this.verificarSeEmailEstaEmUso(email);
                emailEstaEmUso = false;
            } catch (EmailEmUsoException ex) {
                email = buildarEmail(primeiroNome, ultimoNome, num++);
            }

        } while (emailEstaEmUso);

        return email;

    }

    private String buildarEmail(String primeiroNome, String ultimoNome, int num) {
        String pattern = "%s.%s%d@fwauto.com";
        return String.format(pattern, primeiroNome.toLowerCase(), ultimoNome.toLowerCase(), num);
    }


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        System.out.println(usuarioRepository.findByEmail(email));
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

}
