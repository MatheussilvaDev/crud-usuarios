package com.mathdev.usuario.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endereco")
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estado", length = 2)
    private String estado;

    @Column(name = "cidade", length = 100)
    private String cidade;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "cep", length = 9)
    private String cep;

    @Column(name = "rua")
    private String rua;

    @Column(name = "numero", length = 10)
    private int numero;

    @Column(name = "complemento")
    private String complemento;

}
