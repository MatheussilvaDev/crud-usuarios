package com.mathdev.usuario.business.mapper;


import com.mathdev.usuario.business.dto.UsuarioDTO;
import com.mathdev.usuario.infrastructure.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final EnderecoMapper enderecoMapper;
    private final TelefoneMapper telefoneMapper;

    public Usuario toEntity(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(enderecoMapper.toEntityList(usuarioDTO.getEnderecos()))
                .telefones(telefoneMapper.toEntityList(usuarioDTO.getTelefones()))
                .build();
    }

    public UsuarioDTO toDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(enderecoMapper.toDtoList(usuario.getEnderecos()))
                .telefones(telefoneMapper.toDtoList(usuario.getTelefones()))
                .build();
    }
}
