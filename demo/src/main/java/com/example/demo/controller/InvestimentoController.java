package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.Dto.InvestimentoDto;
import com.example.demo.model.Investimento;
import com.example.demo.model.Usuario;
import com.example.demo.service.InvestimentoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/investimento")
public class InvestimentoController {

    @Autowired
    InvestimentoService service;

    @PutMapping("/{id}")
    public ResponseEntity atualizar (@PathVariable("id") Integer idInvestimento, @RequestBody InvestimentoDto request) {

        try {
            Investimento inv = Investimento.builder()
                                            .nome(request.getNome())
                                            .usuario(Usuario.builder().id(request.getIdUsuario()).build()).build();
            Investimento salvo = service.atualizar(inv);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
    
}
