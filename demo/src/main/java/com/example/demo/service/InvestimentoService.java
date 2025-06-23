package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.model.Investimento;
import com.example.demo.model.Posicao;
import com.example.demo.model.Repositorio.InvestimentoRepo;
import com.example.demo.model.Repositorio.PosicaoRepo;
import com.example.demo.model.Repositorio.UsuarioRepo;
import com.example.demo.service.exceptions.InvestimentoRunTime;

@Service
public class InvestimentoService {
    
    @Autowired
    InvestimentoRepo repo;

    @Autowired
    UsuarioRepo usuarioRepo; // auxilar 

    @Autowired
    PosicaoRepo posicaoRepo; // auxilar

    // funções auxiliares 
    // são todos private
    private void verificarId(Investimento inv){
        if(inv == null || inv.getId() == null){
            throw new InvestimentoRunTime("Id do investimento é inválido");
        }
    }

    private void verificarInvestimento(Investimento inv){

        if (inv == null){
            throw new InvestimentoRunTime("Investimento invalido");
        }
        if((inv.getNome() == null )|| (inv.getNome().equals(""))){
             throw new InvestimentoRunTime("Nome do investimento invalido");
        }
        if(inv.getUsuario() == null){
             throw new InvestimentoRunTime("Usuario do investimento invalido");
        }

    }

    private void verificarPosicao(Investimento inv){
        List<Posicao> resultado = posicaoRepo.findByInvestimento(inv);

        if(!resultado.isEmpty()){
            throw new InvestimentoRunTime("Esse investimento possui posição");
        }
    }

    // metodos/funções pricipais

    public Investimento salvar(Investimento inv){
        verificarInvestimento(inv);
        return repo.save(inv);
    }

    public Investimento atualizar(Investimento inv){
        verificarId(inv);
        return salvar(inv);
    }

    public void remover(Investimento inv){
        verificarId(inv);
        verificarPosicao(inv);
        repo.delete(inv);
    }

    public void remover(Integer idInvestimento) {
        Optional<Investimento> inv = repo.findById(idInvestimento);
        remover(inv.get());
    }

    public Double obterValorTotal(Investimento inv) {
        verificarId(inv);
        return repo.obterSaldoInvestimento(inv);
    }

    // ???
    public List<Investimento> buscar (Investimento filtro) {
        Example<Investimento> example =
        Example.of(filtro, ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher(StringMatcher.CONTAINING)); //lik
        return repo.findAll(example); //o rep já deu essa imp
    }
}
