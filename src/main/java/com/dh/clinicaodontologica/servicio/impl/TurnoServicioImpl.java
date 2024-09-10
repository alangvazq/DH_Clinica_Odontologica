package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.excepcion.ApiExcepcion;
import com.dh.clinicaodontologica.mapper.ITurnoMapper;
import com.dh.clinicaodontologica.modelo.Turno;
import com.dh.clinicaodontologica.modelo.dto.TurnoDto;
import com.dh.clinicaodontologica.repositorio.IPacienteRepositorio;
import com.dh.clinicaodontologica.repositorio.ITurnoRepositorio;
import com.dh.clinicaodontologica.servicio.ITurnoServicio;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoServicioImpl implements ITurnoServicio {
    private static final Logger LOGGER = Logger.getLogger(TurnoServicioImpl.class);
    private final ITurnoRepositorio turnoRepositorio;
    private final ITurnoMapper turnoMapper;

    @Override
    public List<TurnoDto> listar() {
        LOGGER.debug("Listando turnos - Servicio");
        return turnoRepositorio.findAll().stream().map(turnoMapper::turnoADto).toList();
    }

    @Override
    public TurnoDto buscar(Long turnoId) {
        LOGGER.debug("Buscando turno por id - Servicio");
        Turno turno = turnoRepositorio.findById(turnoId).orElseThrow(() ->
                ApiExcepcion.recursoNoEncontrado(String.format("El turno de id: %d no existe", turnoId))
        );
        return turnoMapper.turnoADto(turno);
    }

    @Override
    public TurnoDto agregar(TurnoDto datosTurno) {
        LOGGER.debug("Agregando turno - Servicio");
        // TODO Validar que el paciente y el odontologo existan
        Turno turno = turnoRepositorio.save(turnoMapper.dtoATurno(datosTurno));
        return turnoMapper.turnoADto(turno);
    }

    @Override
    public TurnoDto modificar(Long turnoId, TurnoDto datosTurno) {
        LOGGER.debug("Modificando turno por id - Servicio");
        Turno turno = turnoRepositorio.findById(turnoId).orElseThrow(() ->
                ApiExcepcion.recursoNoEncontrado(String.format("El turno de id: %d no existe", turnoId))
        );
        turnoMapper.actualizarTurnoDesdeDto(turno, datosTurno);
        return turnoMapper.turnoADto(turnoRepositorio.save(turno));
    }

    @Override
    public void eliminar(Long turnoId) {
        LOGGER.debug("Eliminando turno por id - Servicio");
        turnoRepositorio.deleteById(turnoId);
    }
}
