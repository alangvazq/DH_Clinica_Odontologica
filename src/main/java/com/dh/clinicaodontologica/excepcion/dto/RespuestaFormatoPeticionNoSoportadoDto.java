package com.dh.clinicaodontologica.excepcion.dto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import java.util.List;

public class RespuestaFormatoPeticionNoSoportadoDto extends RespuestaErrorDto {
    public RespuestaFormatoPeticionNoSoportadoDto(HttpMediaTypeNotSupportedException excepcion, HttpServletRequest peticion) {
        super(generarMensajeError(excepcion), peticion);
    }

    private static String generarMensajeError(HttpMediaTypeNotSupportedException excepcion) {
        List<MediaType> formatosAceptados = excepcion.getSupportedMediaTypes();

        StringBuilder builder = new StringBuilder();
        builder.append("No se aceptan peticion en formato: ").append(excepcion.getContentType());

        if (!formatosAceptados.isEmpty()) {
            builder.append(" . Los formatos aceptados son:");
            formatosAceptados.forEach(formato -> builder.append(" ").append(formato));
        }

        return builder.toString();
    }
}
