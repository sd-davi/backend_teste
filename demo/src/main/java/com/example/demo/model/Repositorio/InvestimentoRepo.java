package com.example.demo.model.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Investimento;
import com.example.demo.model.Usuario;

public interface InvestimentoRepo
extends JpaRepository<Investimento , Integer> {

    public List<Investimento> findByUsuario(Usuario usuario);

    @Query("select sum(p.valor) " +
           "from Posicao p join p.investimento i "+
           "where p.investimento = :investimento ")
    Double obterSaldoInvestimento(@Param("investimento") Investimento inv);

}
