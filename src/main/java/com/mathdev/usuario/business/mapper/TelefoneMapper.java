package com.mathdev.usuario.business.mapper;

import com.mathdev.usuario.business.dto.TelefoneDTO;
import com.mathdev.usuario.infrastructure.entity.Telefone;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelefoneMapper {

    public List<Telefone> toEntityList(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream()
                .map(
                        e -> Telefone.builder()
                                .ddd(e.getDdd())
                                .numero(e.getNumero())
                        .build())
                .toList();
    }

    public List<TelefoneDTO> toDtoList(List<Telefone> telefones){
        return telefones.stream()
                .map(e -> TelefoneDTO.builder()
                        .ddd(e.getDdd())
                        .numero(e.getNumero())
                        .build())
                .toList();
    }
}
