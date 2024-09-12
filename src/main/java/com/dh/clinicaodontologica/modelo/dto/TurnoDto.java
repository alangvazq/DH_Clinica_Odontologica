package com.dh.clinicaodontologica.modelo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class TurnoDto {
    private Long id;

    private LocalDateTime fechaHora;

    @NotNull(message = "El odontologo es obligatorio")
    private OdontologoDto odontologo;

    @NotNull(message = "El paciente es obligatorio")
    private PacienteDto paciente;
}
