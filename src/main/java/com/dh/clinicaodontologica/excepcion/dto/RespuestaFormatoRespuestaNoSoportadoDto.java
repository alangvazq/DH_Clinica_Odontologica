package com.dh.clinicaodontologica.excepcion.dto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.util.List;

public class RespuestaFormatoRespuestaNoSoportadoDto extends RespuestaErrorDto {
    public RespuestaFormatoRespuestaNoSoportadoDto(HttpMediaTypeNotAcceptableException excepcion, HttpServletRequest peticion) {
        super(generarMensajeError(excepcion, peticion), peticion);
    }

    private static String generarMensajeError(
            HttpMediaTypeNotAcceptableException excepcion,
            HttpServletRequest peticion
    ) {

        List<MediaType> formatosAceptados = excepcion.getSupportedMediaTypes();

        StringBuilder builder = new StringBuilder();
        builder.append("No se generan respuestas en el formato: ").append(peticion.getHeader("accept"));

        if(!formatosAceptados.isEmpty()) {
            builder.append(" . Los formatos aceptados son:");
            formatosAceptados.forEach(formato -> builder.append(" ").append(formato));
        }

        return builder.toString();
    }
}
