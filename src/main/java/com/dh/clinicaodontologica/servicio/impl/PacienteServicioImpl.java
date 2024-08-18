package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.modelo.Paciente;
import com.dh.clinicaodontologica.servicio.IPacienteServicio;

import java.util.List;

public class PacienteServicioImpl implements IPacienteServicio {
    private final IDao<Paciente, Long> pacienteDao;

    public PacienteServicioImpl(IDao<Paciente, Long> pacienteDao) {
        this.pacienteDao = pacienteDao;
    }

    @Override
    public List<Paciente> listar() throws Exception {
        return pacienteDao.listar();
    }

    @Override
    public Paciente buscar(Long id) throws Exception {
        return pacienteDao.buscar(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    @Override
    public Paciente agregar(Paciente paciente) throws Exception {
        return pacienteDao.agregar(paciente);
    }

    @Override
    public Paciente modificar(Paciente paciente) throws Exception {
        return pacienteDao.modificar(paciente);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        pacienteDao.eliminar(id);
    }
}
