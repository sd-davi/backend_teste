package com.example.demo.model.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Investimento;
import com.example.demo.model.Posicao;

public interface PosicaoRepo extends JpaRepository<Posicao, Integer>{
    
    public List<Posicao> findByInvestimento(Investimento investimento);
}
