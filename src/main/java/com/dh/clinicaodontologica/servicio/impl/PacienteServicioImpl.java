package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.modelo.Paciente;
import com.dh.clinicaodontologica.servicio.IPacienteServicio;

import java.time.LocalDate;
import java.util.List;

public class PacienteServicioImpl implements IPacienteServicio {
//    private final IDao<Paciente, Long> pacienteDao;

    public PacienteServicioImpl() {
//        this.pacienteDao = pacienteDao;
    }

    @Override
    public List<Paciente> listar() {
//        return pacienteDao.listar();
        return null;
    }

    @Override
    public Paciente buscar(Long id) {
//        return pacienteDao.buscar(id).orElse(null);
        return null;
    }

    @Override
    public Paciente agregar(Paciente paciente) {
//        paciente.setFechaAlta(LocalDate.now());
//        return pacienteDao.agregar(paciente);
        return null;
    }

    @Override
    public Paciente modificar(Paciente paciente) {
//        return pacienteDao.modificar(paciente);
        return null;
    }

    @Override
    public void eliminar(Long id) {
//        pacienteDao.eliminar(id);
    }
}
