package com.dh.clinicaodontologica.servicio;

import com.dh.clinicaodontologica.modelo.Odontologo;

import java.util.List;

public interface IOdontologoServicio {
    List<Odontologo> listar();
    Odontologo buscar(Long id);
    Odontologo agregar(Odontologo odontologo);
    Odontologo modificar(Odontologo odontologo);
    void eliminar(Long id);
}
