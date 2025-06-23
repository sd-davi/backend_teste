package com.example.demo.model;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.model.Repositorio.UsuarioRepo;

@ExtendWith(SpringExtension.class) // permite acessar o codigo principal
@SpringBootTest
public class UsuarioRepoTest {
    // vamos criar os testes, cada teste tem: cenário, ação e verificação
    
    @Autowired // pega a implementação genérica do Jpa e joga no repo
    UsuarioRepo repo;

    @Test
    public void deveSalvarUsuario(){
        //cenário
        Usuario novo = Usuario.builder().nome("Davi")
                                        .email("Davi@gmail.com")
                                        .senha("123").build();
        //ação
        Usuario retorno =  repo.save(novo);
        //verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome());
        Assertions.assertEquals(novo.getEmail(), retorno.getEmail());
        Assertions.assertEquals(novo.getSenha(), retorno.getSenha());

        // rollack
        repo.delete(novo);
    }

    @Test
    public void deveRemoverUsuario(){

    // cenário
    Usuario novo =  Usuario.builder().nome("Luiz")
                                     .email("Luiz@gmail.com")
                                     .senha("000").build();
    // ação
    Usuario salvo = repo.save(novo);
    Integer id = salvo.getId();
    repo.deleteById(id);

    // verificação
    Optional<Usuario> temp = repo.findById(id);
    Assertions.assertFalse(temp.isPresent());
    }
}
