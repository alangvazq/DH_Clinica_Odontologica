package com.dh.clinicaodontologica.servicio;

import com.dh.clinicaodontologica.modelo.Paciente;

import java.util.List;

public interface IPacienteServicio {
    List<Paciente> listar();
    Paciente buscar(Long id);
    Paciente agregar(Paciente paciente);
    Paciente modificar(Paciente paciente);
    void eliminar(Long id);
}
