package com.dh.clinicaodontologica.servicio;

import com.dh.clinicaodontologica.modelo.dto.TurnoDto;

import java.util.List;

public interface ITurnoServicio {
    List<TurnoDto> listar();
    TurnoDto buscarUltimo();
    List<TurnoDto> crear(Long odontologoId);
    TurnoDto asignar(Long turnoId, Long pacienteId);
}
