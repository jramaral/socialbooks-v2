package com.socialbooks.jra.services.exceptions;

public class AutorNaoEncontradoException extends RuntimeException {

    public AutorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public AutorNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
