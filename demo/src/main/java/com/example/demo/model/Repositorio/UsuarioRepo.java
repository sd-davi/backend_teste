package com.example.demo.model.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Usuario;
import com.example.demo.model.dto.InvestimentoSaldo;

public interface UsuarioRepo 
extends JpaRepository<Usuario, Integer>{
    
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("select new com.labprog.patrimonio.model.dto.InvestimentoSaldo(i, sum(p.valor))"+
           "from Posicao p join p.investimento i "+
           "where i.usuario = :usuario " +
           "group by i")
    List<InvestimentoSaldo> obterSaldosInvestimentos(@Param("usuario") Usuario usuario);
}
