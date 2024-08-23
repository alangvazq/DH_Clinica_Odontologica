package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.modelo.Odontologo;
import com.dh.clinicaodontologica.servicio.IOdontologoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoServicioImpl implements IOdontologoServicio {
    private final IDao<Odontologo, Long> odontologoDao;

    @Autowired
    public OdontologoServicioImpl(IDao<Odontologo, Long> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    @Override
    public List<Odontologo> listar() {
        return odontologoDao.listar();
    }

    @Override
    public Odontologo buscar(Long id) {
        return odontologoDao.buscar(id).orElse(null);
    }

    @Override
    public Odontologo agregar(Odontologo odontologo) {
        return odontologoDao.agregar(odontologo);
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        return odontologoDao.modificar(odontologo);
    }

    @Override
    public void eliminar(Long id) {
        odontologoDao.eliminar(id);
    }
}
