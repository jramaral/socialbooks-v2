package com.socialbooks.jra.services;

import com.socialbooks.jra.domain.Livro;
import com.socialbooks.jra.repository.LivrosRepository;
import com.socialbooks.jra.services.exceptions.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class LivroServices {

    @Autowired
    private LivrosRepository livrosRepository;

    public List<Livro> listar(){
        return livrosRepository.findAll();
    }

    public Livro buscarLivro(Long id){
        Livro livro = livrosRepository.findById(id).orElse(null);
        if(Objects.isNull(livro)){
                throw  new LivroNaoEncontradoException("Livro não encontrado");
        }
       return livro;
    }

    public Livro salvarLivro(Livro livro){
        livro.setId(null);
        return livrosRepository.save(livro);
    }

    public void atualizarLivro(Livro livro){
           verificaExistencia(livro.getId());
           livrosRepository.save(livro);
    }

    public void deletarLivro(Long id){
           verificaExistencia(id);
           livrosRepository.deleteById(id);
    }

    private void verificaExistencia(Long id){
          buscarLivro(id);
    }


}
