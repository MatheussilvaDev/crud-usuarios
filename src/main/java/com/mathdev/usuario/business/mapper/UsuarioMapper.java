package com.mathdev.usuario.business.mapper;


import com.mathdev.usuario.business.dto.EnderecoDTO;
import com.mathdev.usuario.business.dto.TelefoneDTO;
import com.mathdev.usuario.business.dto.UsuarioDTO;
import com.mathdev.usuario.infrastructure.entity.Endereco;
import com.mathdev.usuario.infrastructure.entity.Telefone;
import com.mathdev.usuario.infrastructure.entity.Usuario;
import com.mathdev.usuario.infrastructure.exception.ResourceNotFoundException;
import com.mathdev.usuario.infrastructure.repository.EnderecoRepository;
import com.mathdev.usuario.infrastructure.repository.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final EnderecoMapper enderecoMapper;
    private final EnderecoRepository enderecoRepository;

    private final TelefoneRepository telefoneRepository;
    private final TelefoneMapper telefoneMapper;


    public Usuario toEntity(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(enderecoMapper.toEntityList(usuarioDTO.getEnderecos()))
                .telefones(telefoneMapper.toEntityList(usuarioDTO.getTelefones()))
                .build();
    }

    public UsuarioDTO toDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(enderecoMapper.toDtoList(usuario.getEnderecos()))
                .telefones(telefoneMapper.toDtoList(usuario.getTelefones()))
                .build();
    }

    public List<UsuarioDTO> toListDTO(List<Usuario> usuariosDto){
        return usuariosDto.stream()
                .map(this::toDTO)
                .toList();
    }

    public Usuario updateUser(UsuarioDTO usuarioDTO, Usuario usuarioEntity){
        return Usuario.builder()
                .id(usuarioDTO.getId() != null ? usuarioDTO.getId() : usuarioEntity.getId())
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuarioEntity.getNome())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuarioEntity.getEmail())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuarioEntity.getSenha())
                .enderecos(usuarioDTO.getEnderecos() != null ? this.updateListEndereco(usuarioDTO.getEnderecos()) : usuarioEntity.getEnderecos())
                .telefones(usuarioDTO.getTelefones() != null ? this.updateListTelefone(usuarioDTO.getTelefones()) : usuarioEntity.getTelefones())
                .build();

    }

    public List<Endereco> updateListEndereco(List<EnderecoDTO> dtos){
        return dtos.stream()
                .map(this::updateEndereco)
                .toList();
    }

    public Endereco updateEndereco(EnderecoDTO dto){

        if(dto.getId() == null){
            return Endereco.builder()
                    .numero(dto.getNumero())
                    .bairro(dto.getBairro())
                    .estado(dto.getEstado())
                    .complemento(dto.getComplemento())
                    .cep(dto.getCep())
                    .cidade(dto.getCidade())
                    .rua(dto.getRua())
                    .build();
        }

        Endereco entity = enderecoRepository.findById(dto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Endereco não encontrado."));

        return Endereco.builder()
                .id(dto.getId() != null ? dto.getId() : entity.getId())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .bairro(dto.getBairro() != null ? dto.getBairro() : entity.getBairro())
                .estado(dto.getEstado() != null ? dto.getEstado() : entity.getEstado())
                .complemento(dto.getComplemento() != null ? dto.getComplemento() : entity.getComplemento())
                .cep(dto.getCep() != null ? dto.getCep() : entity.getCep())
                .cidade(dto.getCidade() != null ? dto.getCidade() : entity.getCidade())
                .rua(dto.getRua() != null ? dto.getRua() : entity.getRua())
                .build();
    }

    public List<Telefone> updateListTelefone(List<TelefoneDTO> dtos){
        return dtos.stream()
                .map(this::updateTelefone)
                .toList();
    }

    public Telefone updateTelefone(TelefoneDTO dto){
        if(dto.getId() == null){
           return Telefone.builder()
                    .ddd(dto.getDdd())
                    .numero(dto.getNumero())
                    .build();
        }

        Telefone entity = telefoneRepository.findById(dto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Telefone não existe na base de dados")
        );

         return Telefone.builder()
                .id(entity.getId())
                .ddd(dto.getDdd() != null ? dto.getDdd() : entity.getDdd())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .build();
    }
}
