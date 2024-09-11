package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.excepcion.ApiExcepcion;
import com.dh.clinicaodontologica.mapper.IOdontologoMapper;
import com.dh.clinicaodontologica.modelo.Odontologo;
import com.dh.clinicaodontologica.modelo.dto.OdontologoDto;
import com.dh.clinicaodontologica.repositorio.IOdontologoRepositorio;
import com.dh.clinicaodontologica.servicio.IOdontologoServicio;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OdontologoServicioImpl implements IOdontologoServicio {
    private static final Logger LOGGER = Logger.getLogger(OdontologoServicioImpl.class);
    private final IOdontologoRepositorio iOdontologoRepositorio;
    private final IOdontologoMapper iOdontologoMapper;

    @Override
    public List<OdontologoDto> listar() {
        LOGGER.info("Listar todos los odontólogos");
        return iOdontologoRepositorio.findAll().stream().map(iOdontologoMapper::odontologoADto).toList();
    }

    @Override
    public OdontologoDto buscar(Long odontologoId) {
        LOGGER.info("Buscar odontólogo con ID: " + odontologoId);
        Odontologo odontologo = iOdontologoRepositorio.findById(odontologoId).orElseThrow(() ->
                ApiExcepcion.recursoNoEncontrado("No se encontró el odontólogo con id " + odontologoId));
        LOGGER.info("Odontólogo encontrado: " + odontologo);
        return iOdontologoMapper.odontologoADto(odontologo);
    }

    @Override
    public OdontologoDto agregar(OdontologoDto datosOdontologo) {
        LOGGER.info("Agregar nuevo odontólogo: " + datosOdontologo);
        String matricula = datosOdontologo.getMatricula();
        iOdontologoRepositorio.findByMatricula(matricula).ifPresent(odontologo -> {
            throw ApiExcepcion.violacionIntegridad("Ya existe un odontólogo con la matrícula " + matricula);
        });

        Odontologo odontologoAgregado = iOdontologoRepositorio.save(iOdontologoMapper.dtoAOdontologo(datosOdontologo));
        LOGGER.info("Odontólogo agregado con ID: " + odontologoAgregado.getId());
        return iOdontologoMapper.odontologoADto(odontologoAgregado);
    }

    @Override
    public OdontologoDto modificar(Long odontologoId ,OdontologoDto odontologo) {
        LOGGER.info("Modificar odontólogo: " + odontologo);
        Odontologo odontologoExistente = iOdontologoRepositorio.findById(odontologoId).orElseThrow(() ->
                ApiExcepcion.recursoNoEncontrado("No se encontró el odontólogo con id " + odontologoId));

        iOdontologoMapper.actualizarOdontologoDesdeDto(odontologoExistente, odontologo);
        return iOdontologoMapper.odontologoADto(iOdontologoRepositorio.save(odontologoExistente));
    }

    @Override
    public void eliminar(Long odontologoId) {
        LOGGER.info("Eliminar odontólogo con ID: " + odontologoId);
        iOdontologoRepositorio.deleteById(odontologoId);
        LOGGER.info("Odontólogo con ID " + odontologoId + " eliminado");
    }
}
