package com.example.demo.controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// classe de mapeamento do json para objeto
// usado pela camada de controller
public class UsuarioDto {
    private String nome;
    private String email;
    private String senha; 
}
