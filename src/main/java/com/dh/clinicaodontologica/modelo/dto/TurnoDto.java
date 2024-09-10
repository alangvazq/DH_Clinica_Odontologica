package com.dh.clinicaodontologica.modelo.dto;

import com.dh.clinicaodontologica.modelo.Odontologo;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class TurnoDto {
    private Long id;

    private LocalDate fecha;

    //TODO Cambiar a OdontologoDto
    @NotBlank(message = "El odontologo es obligatorio")
    private Odontologo odontologo;

    @NotBlank(message = "El paciente es obligatorio")
    private PacienteDto paciente;


}
