package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Repositorio.UsuarioRepo;
import com.example.demo.model.Usuario;
import com.example.demo.model.dto.InvestimentoSaldo;
import com.example.demo.service.exceptions.UsuarioRunTime;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepo repo;

    public boolean efetuarLogin(String email, String senha){
        Optional<Usuario> user = repo.findByEmail(email);

        if(!user.isPresent()){
            throw new UsuarioRunTime("Email inválido");
        }
        if(!user.get().getSenha().equals(senha)){
            throw new UsuarioRunTime("senha incorreta");
        }
       return true; 
    }


    // salvar

    @Transactional // so salva apos o final (para evitar dados incosistentes), se tiver problemas faz um rollback
    public Usuario salvar( Usuario user){
        verificarUsuario(user);
        return repo.save(user);
    }

    private void verificarUsuario(Usuario user){

        if(user == null){
            throw new UsuarioRunTime("Usuário nulo");
        }
        if(user.getNome() == null || user.getNome().equals("")){
            throw new UsuarioRunTime("Nome inválido");
        }
        if(user.getEmail() ==  null || user.getEmail().equals("")){
            throw new UsuarioRunTime("Email inválido");
        }
        if(user.getSenha() == null  || user.getSenha().equals("")){
            throw new UsuarioRunTime("Senha inválida");
        }
    }

    // consulta

    private void verificarId(Usuario user){
        if ((user == null ) || user.getId() == null){
            throw new UsuarioRunTime("Usuario invalido");
        }
    }

    public List<InvestimentoSaldo> ObterSaldo(Usuario usuario){
        verificarId(usuario);
        return repo.obterSaldosInvestimentos(usuario);
    }

}
