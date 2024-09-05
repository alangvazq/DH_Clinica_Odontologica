package com.dh.clinicaodontologica.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaAlta;
//    private Domicilio domicilio;

    public Paciente() {
    }

//    public Paciente(Long id, String nombre, String apellido, String dni, LocalDate fechaAlta, Domicilio domicilio) {
//        this.id = id;
//        this.nombre = nombre;
//        this.apellido = apellido;
//        this.dni = dni;
//        this.fechaAlta = fechaAlta;
//        this.domicilio = domicilio;
//    }
//
//    public Paciente(String nombre, String apellido, String dni, LocalDate fechaAlta, Domicilio domicilio) {
//        this.nombre = nombre;
//        this.apellido = apellido;
//        this.dni = dni;
//        this.fechaAlta = fechaAlta;
//        this.domicilio = domicilio;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

//    public Domicilio getDomicilio() {
//        return domicilio;
//    }
//
//    public void setDomicilio(Domicilio domicilio) {
//        this.domicilio = domicilio;
//    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaAlta=" + fechaAlta +
//                ", domicilio=" + domicilio +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Paciente paciente = (Paciente) o;
//        return Objects.equals(id, paciente.id) && Objects.equals(nombre, paciente.nombre) && Objects.equals(apellido, paciente.apellido) && Objects.equals(domicilio, paciente.domicilio) && Objects.equals(dni, paciente.dni) && Objects.equals(fechaAlta, paciente.fechaAlta);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, nombre, apellido, domicilio, dni, fechaAlta);
//    }
}
