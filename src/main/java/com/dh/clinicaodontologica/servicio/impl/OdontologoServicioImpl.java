package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.excepcion.ApiExcepcion;
import com.dh.clinicaodontologica.modelo.Odontologo;
import com.dh.clinicaodontologica.repositorio.IOdontologoRepositorio;
import com.dh.clinicaodontologica.servicio.IOdontologoServicio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoServicioImpl implements IOdontologoServicio {
    private static final Logger LOGGER = Logger.getLogger(OdontologoServicioImpl.class);
    @Autowired
    private IOdontologoRepositorio iOdontologoRepositorio;

    @Override
    public List<Odontologo> listar() {
        LOGGER.info("Listar todos los odontólogos");
        List<Odontologo> odontologos = iOdontologoRepositorio.findAll();
        LOGGER.info("Cantidad de odontólogos encontrados: " + odontologos.size());
        return odontologos;
    }

    @Override
    public Odontologo buscar(Long id) {
        LOGGER.info("Buscar odontólogo con ID: " + id);
        Odontologo odontologo = iOdontologoRepositorio.findById(id).orElseThrow(() -> ApiExcepcion.recursoNoEncontrado("No se encontró el odontólogo con id " + id));
        LOGGER.info("Odontólogo encontrado: " + odontologo);
        return odontologo;
    }

    @Override
    public Odontologo agregar(Odontologo odontologo) {
        LOGGER.info("Agregar nuevo odontólogo: " + odontologo);
        Odontologo odontologoAgregado = iOdontologoRepositorio.save(odontologo);
        LOGGER.info("Odontólogo agregado con ID: " + odontologoAgregado.getId());
        return odontologoAgregado;
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        LOGGER.info("Modificar odontólogo: " + odontologo);
        Odontologo odontologoModificado = iOdontologoRepositorio.save(odontologo);
        LOGGER.info("Odontólogo modificado con ID: " + odontologoModificado.getId());
        return odontologoModificado;
    }

    @Override
    public void eliminar(Long id) {
        LOGGER.info("Eliminar odontólogo con ID: " + id);
        iOdontologoRepositorio.deleteById(id);
        LOGGER.info("Odontólogo con ID " + id + " eliminado");
    }
}
