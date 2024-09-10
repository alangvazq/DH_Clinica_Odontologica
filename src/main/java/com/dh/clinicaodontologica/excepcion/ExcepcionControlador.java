package com.dh.clinicaodontologica.excepcion;

import com.dh.clinicaodontologica.excepcion.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcepcionControlador {
    private static final Logger log = Logger.getLogger(ExcepcionControlador.class);
    // Error 400
    // Cuando alguno de los datos de la petición falla las validaciones de formato
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaDatosInvalidosDto> manejarDatosInvalidos(
            MethodArgumentNotValidException excepcion,
            HttpServletRequest peticion
    ) {
        log.error(excepcion.getMessage());
        return ResponseEntity.badRequest().body(new RespuestaDatosInvalidosDto(excepcion, peticion));
    }

    // Error 400
    // Cuando el JSON que envía el cliente está vacío o tiene un formato inválido
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RespuestaErrorDto> manejarJsonPeticionInvalido(
            HttpMessageNotReadableException excepcion,
            HttpServletRequest peticion
    ) {
        log.error(excepcion.getMessage());
        return ResponseEntity.badRequest().body(new RespuestaErrorGenericaDto(excepcion, peticion));
    }

    // Error 405
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RespuestaErrorDto> manejarMetodoNoSoportado(
            HttpRequestMethodNotSupportedException excepcion,
            HttpServletRequest peticion
    ) {
        log.error(excepcion.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                             .body(new RespuestaMetodoNoSoportadoDto(excepcion, peticion));
    }

    // Error 406
    // Cuando el cliente pide que le envíen la respuesta en un formato no soportado (ej: XML)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<RespuestaErrorDto> manejarFormatoPeticionNoSoportado(
            HttpMediaTypeNotAcceptableException excepcion,
            HttpServletRequest peticion
    ) {
        log.error(excepcion.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new RespuestaFormatoRespuestaNoSoportadoDto(excepcion, peticion));
    }

    // Error 415
    // Cuando el cliente manda la petición en un formato no soportado (ej: XML)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<RespuestaErrorDto> manejarFormatoRespuestaNoSoportado(
            HttpMediaTypeNotSupportedException excepcion,
            HttpServletRequest peticion
    ) {
        log.error(excepcion.getMessage());
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                             .body(new RespuestaFormatoPeticionNoSoportadoDto(excepcion, peticion));
    }

    // Error 400, 404
    // Cuando la excepción fue lanzada desde los servicios
    @ExceptionHandler(ApiExcepcion.class)
    public ResponseEntity<RespuestaErrorDto> manejarApiExcepcion(
            ApiExcepcion excepcion, HttpServletRequest peticion
    ) {
        log.error(excepcion.getMessage());
        return ResponseEntity.status(excepcion.getCodigoEstado())
                             .body(new RespuestaErrorGenericaDto(excepcion, peticion));
    }

    // Error 500
    // Cualquier otra excepción
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaErrorDto> manejarErrorInterno(
            Exception excepcion, HttpServletRequest peticion
    ) {
        log.error(excepcion.getMessage());
        return ResponseEntity.internalServerError().body(new RespuestaErrorGenericaDto(excepcion, peticion));
    }
}
