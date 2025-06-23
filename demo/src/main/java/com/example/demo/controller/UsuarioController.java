package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.Dto.UsuarioDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.service.UsuarioService;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @PostMapping("/salvar") // os dados são passados no corpo da mensagem 
    public ResponseEntity salvar(@RequestBody UsuarioDto usuarioRequest){ 
        // devolve um objeto entity, ou seja o usuario salvo
        // transforma o que tem no body em um dto

    }

}
