package com.socialbooks.jra.handler;

import com.socialbooks.jra.domain.DetalheErro;
import com.socialbooks.jra.services.exceptions.AutorNaoEncontradoException;
import com.socialbooks.jra.services.exceptions.LivroNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<DetalheErro> handleLivroNaoEncontradoException(LivroNaoEncontradoException e, HttpServletRequest request){

        DetalheErro erros = new DetalheErro();
        erros.setStatus(404L);
        erros.setTitulo("O Livro não pode ser encontrado!");
        erros.setMensagemDesenvolvedor("http://erros.jrabooks.com/404");
        erros.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erros);
    }

    @ExceptionHandler(AutorNaoEncontradoException.class)
    public ResponseEntity<DetalheErro> handleAutorNaoEncontradoException(AutorNaoEncontradoException e, HttpServletRequest request){

        DetalheErro erros = new DetalheErro();
        erros.setStatus(404L);
        erros.setTitulo("O Autor não pode ser encontrado!");
        erros.setMensagemDesenvolvedor("http://erros.jrabooks.com/404");
        erros.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erros);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DetalheErro> handleAutorNaoEncontradoException(DataIntegrityViolationException e, HttpServletRequest request){

        DetalheErro erros = new DetalheErro();
        erros.setStatus(400L);
        erros.setTitulo("Requisição inválida!");
        erros.setMensagemDesenvolvedor("http://erros.jrabooks.com/404");
        erros.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

}
