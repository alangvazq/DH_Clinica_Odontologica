package com.dh.clinicaodontologica.excepcion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RespuestaErrorDto {
    private final LocalDateTime fechaYHora = LocalDateTime.now();
    private final String ruta;
    private final String mensaje;

    public RespuestaErrorDto(String mensaje,HttpServletRequest peticion) {
        this.ruta = peticion.getRequestURI();
        this.mensaje = mensaje;
    }
}
