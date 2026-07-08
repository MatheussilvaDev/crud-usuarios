package com.mathdev.usuario.business.mapper;

import com.mathdev.usuario.business.dto.EnderecoDTO;
import com.mathdev.usuario.infrastructure.entity.Endereco;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnderecoMapper {

    public List<Endereco> toEntityList(List<EnderecoDTO> enderecoDTO){
        return enderecoDTO.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<EnderecoDTO> toDtoList(List<Endereco> enderecos){
        return enderecos.stream()
                .map(this::toDTO)
                .toList();
    }

    public Endereco toEntity(EnderecoDTO dto){
        return Endereco.builder()
                .id(dto.getId())
                .estado(dto.getEstado())
                .cidade(dto.getCidade())
                .bairro(dto.getBairro())
                .cep(dto.getCep())
                .rua(dto.getRua())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .build();
    }

    public EnderecoDTO toDTO(Endereco entity){
        return EnderecoDTO.builder()
                .id(entity.getId())
                .estado(entity.getEstado())
                .cidade(entity.getCidade())
                .bairro(entity.getBairro())
                .cep(entity.getCep())
                .rua(entity.getRua())
                .numero(entity.getNumero())
                .complemento(entity.getComplemento())
                .build();
    }

    public Endereco updateEndereco(Endereco entity, EnderecoDTO dto){
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

}
