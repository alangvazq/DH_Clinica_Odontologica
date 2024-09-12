package com.dh.clinicaodontologica.excepcion;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiExcepcion extends RuntimeException {
    private final HttpStatus codigoEstado;

    private ApiExcepcion(String message, HttpStatus codigoEstado) {
        super(message);
        this.codigoEstado = codigoEstado;
    }

    public static ApiExcepcion recursoNoEncontrado(String message) {
        return new ApiExcepcion(message, HttpStatus.NOT_FOUND);
    }

    public static ApiExcepcion conflictoPorViolacionDeEstado(String message) {
        return new ApiExcepcion(message, HttpStatus.CONFLICT);
    }
}
