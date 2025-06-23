package com.example.demo.controller.Dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PosicaoDto {
    private Double valor;
    private Date data;
    private Integer idInvestimento;
}
