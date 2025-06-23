package com.example.demo.model.dto;

import com.example.demo.model.Investimento;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvestimentoSaldo {
    public Investimento inv;
    public Double valor;
}
