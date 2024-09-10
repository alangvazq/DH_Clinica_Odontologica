package com.dh.clinicaodontologica.modelo.dto;

import com.dh.clinicaodontologica.modelo.Odontologo;
import com.dh.clinicaodontologica.modelo.Paciente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
public class TurnoDto {
    private Long id;

    private LocalDate fecha;

    //TODO Cambiar a OdontologoDto
    @NotNull(message = "El odontologo es obligatorio")
    private Odontologo odontologo;

    @NotNull(message = "El paciente es obligatorio")
    private PacienteDto paciente;
}
