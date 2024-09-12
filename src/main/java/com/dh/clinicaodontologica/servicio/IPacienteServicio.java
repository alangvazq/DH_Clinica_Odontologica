package com.dh.clinicaodontologica.servicio;

import com.dh.clinicaodontologica.modelo.dto.PacienteDto;

import java.util.List;

public interface IPacienteServicio {
    List<PacienteDto> listar();
    PacienteDto buscarPorId(Long pacienteId);
    PacienteDto buscarPorDni(String pacienteDni);
    PacienteDto agregar(PacienteDto datosPaciente);
    PacienteDto modificar(Long pacienteId, PacienteDto datosPaciente);
    void eliminar(Long pacienteId);
}
