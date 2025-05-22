package com.maosencantadas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RecursoEncontradoException extends RuntimeException {

    public RecursoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
