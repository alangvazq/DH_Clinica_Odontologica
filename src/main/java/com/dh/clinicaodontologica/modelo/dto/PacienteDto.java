package com.dh.clinicaodontologica.modelo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class PacienteDto {
    private Long id;

    @NotBlank(message = "El nombre no puede quedar en blanco")
    private String nombre;

    @NotBlank(message = "El apellido no puede quedar en blanco")
    private String apellido;

    @NotBlank(message = "El DNI no puede quedar en blanco")
    private String dni;

    private LocalDate fechaAlta;

    @NotNull(message = "El domicilio es obligatorio")
    private DomicilioDto domicilio;
}
