package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.modelo.Domicilio;
import com.dh.clinicaodontologica.servicio.IDomicilioServicio;

import java.util.List;

public class DomicilioServicioImpl implements IDomicilioServicio {
    private final IDao<Domicilio, Long> domicilioDao;

    public DomicilioServicioImpl(IDao<Domicilio, Long> domicilioDao) {
        this.domicilioDao = domicilioDao;
    }

    @Override
    public List<Domicilio> listar() throws Exception {
        return domicilioDao.listar();
    }

    @Override
    public Domicilio buscar(Long id) throws Exception {
        return domicilioDao.buscar(id).orElseThrow(() -> new RuntimeException("Domicilio no encontrado"));
    }

    @Override
    public Domicilio agregar(Domicilio domicilio) throws Exception {
        return domicilioDao.agregar(domicilio);
    }

    @Override
    public Domicilio modificar(Domicilio domicilio) throws Exception {
        return domicilioDao.modificar(domicilio);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        domicilioDao.eliminar(id);
    }

}
