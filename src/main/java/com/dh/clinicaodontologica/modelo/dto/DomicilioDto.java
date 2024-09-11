package com.dh.clinicaodontologica.modelo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DomicilioDto {
    private Long id;
    private String calle;

    @Positive(message = "El número debe ser un entero positivo")
    private Integer numero;

    @NotBlank(message = "La localidad no puede quedar en blanco")
    private String localidad;

    @NotBlank(message = "La provincia no puede quedar en blanco")
    private String provincia;
}
