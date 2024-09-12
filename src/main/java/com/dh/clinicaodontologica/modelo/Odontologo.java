package com.dh.clinicaodontologica.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "odontologos")
@Getter
@Setter
@ToString
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String matricula;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private Set<Turno> turnos;
}
