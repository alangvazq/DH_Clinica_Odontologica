package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.excepcion.ApiExcepcion;
import com.dh.clinicaodontologica.mapper.ITurnoMapper;
import com.dh.clinicaodontologica.modelo.Odontologo;
import com.dh.clinicaodontologica.modelo.Paciente;
import com.dh.clinicaodontologica.modelo.Turno;
import com.dh.clinicaodontologica.modelo.dto.TurnoDto;
import com.dh.clinicaodontologica.repositorio.IOdontologoRepositorio;
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
    private final IPacienteRepositorio pacienteRepositorio;
    private final IOdontologoRepositorio odontologoRepositorio;

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
        Odontologo odontologo = odontologoRepositorio.findById(datosTurno.getOdontologo().getId()).orElse(null);
        Paciente paciente = pacienteRepositorio.findById(datosTurno.getPaciente().getId()).orElse(null);

        if (odontologo != null && paciente != null) {
            Turno turno = turnoMapper.dtoATurno(datosTurno);
            turno.setOdontologo(odontologo);
            turno.setPaciente(paciente);
            Turno turnoGuardado = turnoRepositorio.save(turno);
            return turnoMapper.turnoADto(turnoGuardado);
        } else {
            throw ApiExcepcion.recursoNoEncontrado("Odontólogo o paciente no encontrado");
        }
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
