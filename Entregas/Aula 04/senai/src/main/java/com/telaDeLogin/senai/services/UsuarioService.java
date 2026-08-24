package com.telaDeLogin.senai.services;

import com.telaDeLogin.senai.dtos.UsuarioDto;
import com.telaDeLogin.senai.entities.UsuarioEntity;
import com.telaDeLogin.senai.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;


    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioDto realizarLogin(UsuarioDto usuarioDto) {
        Optional<UsuarioEntity> usuarioOP = this.repository.findByEmailAndSenha(usuarioDto.getEmail(), usuarioDto.getSenha());
        UsuarioDto usuarioDtoRetorno = new UsuarioDto();
        if (usuarioOP.isPresent()) {
            usuarioDtoRetorno = this.converterEntityParaDto((UsuarioEntity) usuarioOP.get());
            return usuarioDtoRetorno;
        } else {
            return usuarioDtoRetorno;
        }

    }

    private UsuarioDto converterEntityParaDto(UsuarioEntity usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setMatricula(usuario.getMatricula());
        usuarioDto.setDataNascimento(usuario.getDataNascimento());
        return usuarioDto;
    }
}