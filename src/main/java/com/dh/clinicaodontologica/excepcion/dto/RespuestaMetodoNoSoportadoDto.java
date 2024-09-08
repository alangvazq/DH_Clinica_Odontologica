package com.dh.clinicaodontologica.excepcion.dto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Set;

public class RespuestaMetodoNoSoportadoDto extends RespuestaErrorDto {

    public RespuestaMetodoNoSoportadoDto(HttpRequestMethodNotSupportedException excepcion, HttpServletRequest peticion) {
        super(generarMensajeError(excepcion), peticion);
    }

    private static String generarMensajeError(HttpRequestMethodNotSupportedException excepcion) {
        StringBuilder builder = new StringBuilder();
        builder.append("El método usado: ").append(excepcion.getMethod()).append(" no está permitido.");

        Set<HttpMethod> metodosPermitidos = excepcion.getSupportedHttpMethods();

        if (metodosPermitidos != null && !metodosPermitidos.isEmpty()) {
            builder.append(" Los métodos permitidos son:");
            metodosPermitidos.forEach(metodo -> builder.append(" ").append(metodo));
        }

        return builder.toString();
    }
}
