package com.mathdev.usuario.business.mapper;

import com.mathdev.usuario.business.dto.EnderecoDTO;
import com.mathdev.usuario.infrastructure.entity.Endereco;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnderecoMapper {

    public List<Endereco> toEntityList(List<EnderecoDTO> enderecoDTO){
        return enderecoDTO.stream().map(
                e -> Endereco.builder()
                        .cep(e.getCep())
                        .rua(e.getRua())
                        .cidade(e.getCidade())
                        .complemento(e.getComplemento())
                        .estado(e.getEstado())
                        .bairro(e.getBairro())
                        .numero(e.getNumero())
                .build())
                .toList();
    }

    public List<EnderecoDTO> toDtoList(List<Endereco> enderecos){
        return enderecos.stream().map(
                        e -> EnderecoDTO.builder()
                                .cep(e.getCep())
                                .rua(e.getRua())
                                .cidade(e.getCidade())
                                .complemento(e.getComplemento())
                                .estado(e.getEstado())
                                .bairro(e.getBairro())
                                .numero(e.getNumero())
                                .build())
                .toList();
    }
}
