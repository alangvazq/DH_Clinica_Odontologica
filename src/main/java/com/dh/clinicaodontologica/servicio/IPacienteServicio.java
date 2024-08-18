package com.dh.clinicaodontologica.servicio;

import com.dh.clinicaodontologica.modelo.Paciente;

import java.util.List;

public interface IPacienteServicio {
    List<Paciente> listar() throws Exception;
    Paciente buscar(Long id) throws Exception;
    Paciente agregar(Paciente paciente) throws Exception;
    Paciente modificar(Paciente paciente) throws Exception;
    void eliminar(Long id) throws Exception;
}
