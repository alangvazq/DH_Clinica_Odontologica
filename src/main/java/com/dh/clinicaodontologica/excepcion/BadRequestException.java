package com.dh.clinicaodontologica.excepcion;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
