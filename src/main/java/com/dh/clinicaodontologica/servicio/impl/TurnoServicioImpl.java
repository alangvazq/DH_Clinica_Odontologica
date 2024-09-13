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
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        return turnoRepositorio.findTurnosSinAsignar().stream().map(turnoMapper::turnoADto).toList();
    }

    @Override
    public TurnoDto buscarUltimo() {
        LOGGER.debug("Listando turnos - Servicio");
        Turno ultimoTurno = turnoRepositorio.findUltimoTurno().orElseThrow(() -> ApiExcepcion.recursoNoEncontrado("No hay turnos disponibles"));
        return turnoMapper.turnoADto(ultimoTurno);
    }

    @Override
    public List<TurnoDto> crear(Long odontologoId) {
        LOGGER.debug("Creando turno - Servicio");
        Odontologo odontologo = odontologoRepositorio.findById(odontologoId).orElseThrow(() ->
            ApiExcepcion.recursoNoEncontrado(String.format("El odontólogo de ID: %d no existe", odontologoId))
        );

        LocalDateTime fechaHoraActual = LocalDateTime.now();
        LocalDateTime fechaHoraUltimoTurno = turnoRepositorio
                                                .findUltimoTurno()
                                                .map(Turno::getFechaHora)
                                                .orElse(LocalDateTime.now());

        fechaHoraUltimoTurno = fechaHoraUltimoTurno.isBefore(fechaHoraActual) ? fechaHoraActual : fechaHoraUltimoTurno;

        List<LocalDateTime> fechasHorasProximosTurnos = getFechasHorasProximosTurnos(fechaHoraUltimoTurno);
        List<Turno> turnos = fechasHorasProximosTurnos.stream().map(fechaHora -> {
            Turno turno = new Turno();
            turno.setOdontologo(odontologo);
            turno.setFechaHora(fechaHora);
            return turno;
        }).toList();

        return turnoRepositorio.saveAll(turnos).stream().map(turnoMapper::turnoADto).toList();
    }

    @Override
    public TurnoDto asignar(Long turnoId, Long pacienteId) {
        LOGGER.debug("Asignando turno - Servicio");
        Turno turno = turnoRepositorio.findById(turnoId).orElseThrow(() ->
            ApiExcepcion.recursoNoEncontrado(String.format("El turno de ID: %d no existe", turnoId))
        );

        if(turno.getPaciente() != null) {
            throw ApiExcepcion.conflictoPorViolacionDeEstado(String.format("El turno de ID: %d ya está ocupado", turnoId));
        }

        Paciente paciente = pacienteRepositorio.findById(pacienteId).orElseThrow(() ->
            ApiExcepcion.recursoNoEncontrado(String.format("El paciente de ID: %d no existe", pacienteId))
        );

        turno.setPaciente(paciente);
        return turnoMapper.turnoADto(turnoRepositorio.save(turno));
    }

    private List<LocalDateTime> getFechasHorasProximosTurnos(LocalDateTime fechaHoraUltimoTurno) {
        List<LocalDateTime> fechasHoras = new ArrayList<>();
        LocalDateTime ultimaFechaHora = fechaHoraUltimoTurno;
        for (int i = 0; i < 3; i++) {
            LocalDateTime fechaHoraTurno = ultimaFechaHora.plusHours(1);

            if (fechaHoraTurno.getHour() > 11) {
                fechaHoraTurno = fechaHoraTurno.plusDays(1).withHour(6);
            }

            if (fechaHoraTurno.getDayOfWeek() == DayOfWeek.SUNDAY) {
                fechaHoraTurno = fechaHoraTurno.plusDays(1).withHour(6);
            }

            fechasHoras.add(fechaHoraTurno);
            ultimaFechaHora = fechaHoraTurno;
        }
        return fechasHoras;
    }
}
