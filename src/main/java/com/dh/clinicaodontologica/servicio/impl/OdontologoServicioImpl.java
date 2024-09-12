package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.excepcion.ApiExcepcion;
import com.dh.clinicaodontologica.mapper.IOdontologoMapper;
import com.dh.clinicaodontologica.modelo.Odontologo;
import com.dh.clinicaodontologica.modelo.dto.OdontologoDto;
import com.dh.clinicaodontologica.repositorio.IOdontologoRepositorio;
import com.dh.clinicaodontologica.repositorio.ITurnoRepositorio;
import com.dh.clinicaodontologica.servicio.IOdontologoServicio;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OdontologoServicioImpl implements IOdontologoServicio {
    private static final Logger LOGGER = Logger.getLogger(OdontologoServicioImpl.class);
    private final IOdontologoRepositorio odontologoRepositorio;
    private final ITurnoRepositorio turnoRepositorio;
    private final IOdontologoMapper odontologoMapper;

    @Override
    public List<OdontologoDto> listar() {
        LOGGER.info("Listar todos los odontólogos");
        return odontologoRepositorio.findAll().stream().map(odontologoMapper::odontologoADto).toList();
    }

    @Override
    public OdontologoDto buscar(Long odontologoId) {
        LOGGER.info("Buscar odontólogo con ID: " + odontologoId);
        Odontologo odontologo = odontologoRepositorio.findById(odontologoId).orElseThrow(() ->
                ApiExcepcion.recursoNoEncontrado("No se encontró el odontólogo con ID: " + odontologoId));
        LOGGER.info("Odontólogo encontrado: " + odontologo);
        return odontologoMapper.odontologoADto(odontologo);
    }

    @Override
    public OdontologoDto agregar(OdontologoDto datosOdontologo) {
        LOGGER.info("Agregar nuevo odontólogo: " + datosOdontologo);
        String matricula = datosOdontologo.getMatricula();
        odontologoRepositorio.findByMatricula(matricula).ifPresent(odontologo -> {
            throw ApiExcepcion.conflictoPorViolacionDeEstado("Ya existe un odontólogo con la matrícula: " + matricula);
        });
        Odontologo odontologoAgregado = odontologoRepositorio.save(odontologoMapper.dtoAOdontologo(datosOdontologo));
        LOGGER.info("Odontólogo agregado con ID: " + odontologoAgregado.getId());
        return odontologoMapper.odontologoADto(odontologoAgregado);
    }

    @Override
    public OdontologoDto modificar(Long odontologoId ,OdontologoDto odontologo) {
        LOGGER.info("Modificar odontólogo: " + odontologo);
        Odontologo odontologoExistente = odontologoRepositorio.findById(odontologoId).orElseThrow(() ->
                ApiExcepcion.recursoNoEncontrado("No se encontró el odontólogo con ID: " + odontologoId));

        odontologoMapper.actualizarOdontologoDesdeDto(odontologoExistente, odontologo);
        return odontologoMapper.odontologoADto(odontologoRepositorio.save(odontologoExistente));
    }

    @Override
    public void eliminar(Long odontologoId) {
        LOGGER.info("Eliminar odontólogo con ID: " + odontologoId);
        odontologoRepositorio.deleteById(odontologoId);
        LOGGER.info("Odontólogo con ID: " + odontologoId + " eliminado");
    }
}
