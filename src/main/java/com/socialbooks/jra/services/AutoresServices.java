package com.socialbooks.jra.services;

import com.socialbooks.jra.domain.Autor;
import com.socialbooks.jra.repository.AutoresRepository;
import com.socialbooks.jra.services.exceptions.AutorNaoEncontradoException;
import com.socialbooks.jra.services.exceptions.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AutoresServices {

    @Autowired
    private AutoresRepository autorRepository;

    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    public Autor buscarAutor(Long id){
        Autor autor = autorRepository.findById(id).orElse(null);
        if(Objects.isNull(autor)){
            throw new AutorNaoEncontradoException("Autor não encontrado!");
        }
        return autor;
    }

    public Autor salvarAutor(Autor autor) {
        autor.setId(null);
        return autorRepository.save(autor);
    }

    public void removerAutor(Long autorId) {
     buscarAutor(autorId);
      verificarDependencia(autorId);

      autorRepository.deleteById(autorId);


    }

    public void atualizarAutor(Autor autor) {
        buscarAutor(autor.getId());
        autorRepository.save(autor);
    }

    private void verificarDependencia(Long id){
        if(autorRepository.existsByLivros_AutorId(id)){
            throw new AutorNaoEncontradoException("Autor está relacionando em um livro!");
        }
    }
}
