package com.mathdev.usuario.business;

import com.mathdev.usuario.business.dto.EnderecoDTO;
import com.mathdev.usuario.business.mapper.EnderecoMapper;
import com.mathdev.usuario.infrastructure.entity.Endereco;
import com.mathdev.usuario.infrastructure.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    public EnderecoDTO updateEndereco(EnderecoDTO dto){

        Endereco entity = enderecoRepository.getReferenceById(dto.getId());
        Endereco enderecoUpdated = enderecoMapper.updateEndereco(entity, dto);

        return enderecoMapper.toDTO(enderecoRepository.save(enderecoUpdated));
    }
}
