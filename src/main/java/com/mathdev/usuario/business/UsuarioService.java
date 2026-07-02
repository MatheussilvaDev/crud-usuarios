package com.mathdev.usuario.business;

import com.mathdev.usuario.business.dto.UsuarioDTO;
import com.mathdev.usuario.business.mapper.UsuarioMapper;
import com.mathdev.usuario.infrastructure.entity.Usuario;
import com.mathdev.usuario.infrastructure.exception.ConflictException;
import com.mathdev.usuario.infrastructure.exception.ResourceNotFoundException;
import com.mathdev.usuario.infrastructure.repository.UsuarioRepository;
import com.mathdev.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioDTO createUser(UsuarioDTO usuarioDTO){
        boolean existsEmail = usuarioRepository.existsByEmail(usuarioDTO.getEmail());
        if(existsEmail){
            throw new ConflictException("Já existe um registro com o e-mail informado.");
        }

        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO));

        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO findByEmail(String email){
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("E-mail não encontrado"));
        return usuarioMapper.toDTO(usuario);
    }

    public List<UsuarioDTO> findAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarioMapper.toListDTO(usuarios);
    }

    public UsuarioDTO findById(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        return usuarioMapper.toDTO(usuario);
    }

    public void deleteByEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO updateUser(String token, UsuarioDTO usuarioDTO){
        String email = jwtUtil.extractUsername(token.substring(7));

        usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()) : null);

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado")
        );

        Usuario usuarioUpdated = usuarioMapper.updateUser(usuarioDTO, usuario);

        return usuarioMapper.toDTO(usuarioRepository.save(usuarioUpdated));
    }
}
