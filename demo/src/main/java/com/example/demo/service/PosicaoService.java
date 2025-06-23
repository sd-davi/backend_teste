package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.Posicao;
import com.example.demo.model.Repositorio.InvestimentoRepo;
import com.example.demo.model.Repositorio.PosicaoRepo;
import com.example.demo.service.exceptions.PosicaoRunTime;

@Service
public class PosicaoService {
    @Autowired
    PosicaoRepo repo;

    @Autowired
    InvestimentoRepo investimentoRep;

    // metodos aux

    private void verificarPosicao(Posicao pos){
        if(pos == null)
            throw new PosicaoRunTime("Posição inválida");
        if(pos.getInvestimento() == null)
            throw new PosicaoRunTime("Investimento ivalido");
        if(pos.getValor() == null)
            throw new PosicaoRunTime("Valor ivalido");
    }

    private void verificarId(Posicao pos){
        if(pos.getId() == null  || pos.getId().equals(""))
            throw new PosicaoRunTime("Id invalido");
    }

    public Posicao salvar(Posicao pos) {
        verificarPosicao(pos);
        return repo.save(pos);
    }
    public Posicao atualizar(Posicao pos) {
        verificarId(pos);
        return salvar(pos);
    }
    public void remover(Posicao pos) {
        verificarId(pos);
        verificarPosicao(pos);
        repo.delete(pos);
    }
    // ??
    public List<Posicao> buscar (Posicao filtro) {
        Example<Posicao> example =
        Example.of(filtro, ExampleMatcher.matchingAny());
        return repo.findAll(example);
    }
}
