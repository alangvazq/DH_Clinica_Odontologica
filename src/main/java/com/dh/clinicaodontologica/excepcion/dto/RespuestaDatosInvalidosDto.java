package com.dh.clinicaodontologica.excepcion.dto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RespuestaDatosInvalidosDto extends RespuestaErrorDto {
    private final Map<String, String> errores;

    public RespuestaDatosInvalidosDto(MethodArgumentNotValidException excepcion, HttpServletRequest peticion) {
        super("Formato de datos de petición inválido", peticion);
        this.errores = new HashMap<>();
        excepcion.getFieldErrors().forEach(error -> this.errores.put(error.getField(), error.getDefaultMessage()));
    }
}
