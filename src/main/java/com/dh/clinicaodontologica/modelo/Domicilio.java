package com.dh.clinicaodontologica.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "domicilios")
@Getter
@Setter
@ToString
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private Integer numero;

    @Column(nullable = false)
    private String localidad;

    @Column(nullable = false)
    private String provincia;
}
