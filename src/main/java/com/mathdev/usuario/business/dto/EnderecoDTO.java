package com.mathdev.usuario.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoDTO {

    private Long id;
    private String estado;
    private String cidade;
    private String bairro;
    private String cep;
    private String rua;
    private Long numero;
    private String complemento;
}
