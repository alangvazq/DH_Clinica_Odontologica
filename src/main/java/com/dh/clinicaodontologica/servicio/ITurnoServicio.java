package com.dh.clinicaodontologica.servicio;

import com.dh.clinicaodontologica.modelo.Turno;
import com.dh.clinicaodontologica.modelo.dto.TurnoDto;

import java.util.List;

public interface ITurnoServicio {
    List<TurnoDto> listar();
    TurnoDto buscar(Long turnoId);
    TurnoDto agregar(TurnoDto datosTurno);
    TurnoDto modificar(Long turnoId, TurnoDto datosTurno);
    void eliminar(Long turnoId);
}
