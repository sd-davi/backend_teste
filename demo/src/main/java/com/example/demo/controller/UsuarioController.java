package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.Dto.UsuarioDto;
import com.example.demo.model.Usuario;
import com.example.demo.model.dto.InvestimentoSaldo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.service.UsuarioService;
import com.example.demo.service.exceptions.UsuarioRunTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @PostMapping("/salvar") // os dados são passados no corpo da mensagem 
    public ResponseEntity salvar(@RequestBody UsuarioDto usuarioRequest){ 
        // devolve um objeto entity, ou seja o usuario salvo
        // transforma o que tem no body em um dto

        Usuario user = Usuario.builder()
                              .nome(usuarioRequest.getNome())
                              .email(usuarioRequest.getEmail())
                              .senha(usuarioRequest.getSenha()).build();
        
        // tentando salvar o aluno - vai se comunicar com a camda service
        try {
            Usuario salvo = service.salvar(user);
            return new ResponseEntity(salvo,HttpStatus.CREATED);
        } catch (UsuarioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody UsuarioDto resquest) {

        try {
            boolean valor = service.efetuarLogin(resquest.getEmail(), resquest.getSenha());
            return new ResponseEntity(valor,HttpStatus.OK); 
        } catch (UsuarioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }

    @GetMapping("/saldos")
    public ResponseEntity obterSaldos(@RequestParam(value = "usuario", required = true) Integer IdUsuario) {

        try {
            List<InvestimentoSaldo> inv = service.ObterSaldo(Usuario.builder().id(IdUsuario).build());
            return new ResponseEntity(inv, HttpStatus.OK);
        } catch (UsuarioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    

}
