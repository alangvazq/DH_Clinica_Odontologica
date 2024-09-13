package com.dh.clinicaodontologica.modelo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OdontologoDto {
    private Long id;

    @NotBlank(message = "El nombre no puede quedar en blanco")
    private String nombre;

    @NotBlank(message = "El apellido no puede quedar en blanco")
    private String apellido;

    @NotBlank(message = "La matricula no puede quedar en blanco")
    private String matricula;
}
