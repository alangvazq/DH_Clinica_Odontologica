package com.dh.clinicaodontologica.excepcion.dto;

import jakarta.servlet.http.HttpServletRequest;

public class RespuestaErrorGenericaDto extends RespuestaErrorDto {
    public RespuestaErrorGenericaDto(Exception excepcion, HttpServletRequest peticion) {
        super(excepcion.getMessage(), peticion);
    }
}
