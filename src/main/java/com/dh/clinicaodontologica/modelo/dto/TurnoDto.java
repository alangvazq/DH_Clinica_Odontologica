package com.dh.clinicaodontologica.modelo.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class TurnoDto {
    private Long id;
    private LocalDateTime fechaHora;
    private OdontologoDto odontologo;
    private PacienteDto paciente;
}
