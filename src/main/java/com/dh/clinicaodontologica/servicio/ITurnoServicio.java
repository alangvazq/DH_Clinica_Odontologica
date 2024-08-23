package com.dh.clinicaodontologica.servicio;

import com.dh.clinicaodontologica.modelo.Turno;

import java.util.List;

public interface ITurnoServicio {
    List<Turno> listar();
    Turno buscar(Long id);
    Turno agregar(Turno turno);
}
