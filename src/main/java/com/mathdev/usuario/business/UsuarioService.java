package com.mathdev.usuario.business;

import com.mathdev.usuario.business.dto.UsuarioDTO;
import com.mathdev.usuario.business.mapper.UsuarioMapper;
import com.mathdev.usuario.infrastructure.entity.Usuario;
import com.mathdev.usuario.infrastructure.exception.ConflictException;
import com.mathdev.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioDTO createUser(UsuarioDTO usuarioDTO){
        boolean existsEmail = usuarioRepository.existsByEmail(usuarioDTO.getEmail());
        if(existsEmail){
            throw new ConflictException("Já existe um registro com o e-mail informado.");
        }

        Usuario usuario = usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO));

        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO findByEmail(String email){
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ConflictException("E-mail não encontrado"));

        return usuarioMapper.toDTO(usuario);
    }

    public void deleteByEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
}
