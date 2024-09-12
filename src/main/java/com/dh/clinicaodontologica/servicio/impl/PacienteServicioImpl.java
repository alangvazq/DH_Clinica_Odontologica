package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.excepcion.ApiExcepcion;
import com.dh.clinicaodontologica.mapper.IPacienteMapper;
import com.dh.clinicaodontologica.modelo.Paciente;
import com.dh.clinicaodontologica.modelo.dto.PacienteDto;
import com.dh.clinicaodontologica.repositorio.IPacienteRepositorio;
import com.dh.clinicaodontologica.repositorio.ITurnoRepositorio;
import com.dh.clinicaodontologica.servicio.IPacienteServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl implements IPacienteServicio {
    private static final Logger log = Logger.getLogger(PacienteServicioImpl.class);
    private final IPacienteRepositorio pacienteRepositorio;
    private final ITurnoRepositorio turnoRepositorio;
    private final IPacienteMapper pacienteMapper;

    @Override
    public List<PacienteDto> listar() {
        log.debug("Listando pacientes - Servicio");
         return pacienteRepositorio.findAll().stream().map(pacienteMapper::pacienteADto).toList();
    }

    @Override
    public PacienteDto buscarPorId(Long pacienteId) {
        log.debug("Buscando paciente por ID - Servicio");
        Paciente paciente = pacienteRepositorio.findById(pacienteId).orElseThrow(() ->
            ApiExcepcion.recursoNoEncontrado(String.format("El paciente de ID: %d no existe", pacienteId))
        );
        return pacienteMapper.pacienteADto(paciente);
    }

    @Override
    public PacienteDto buscarPorDni(String pacienteDni) {
        log.debug("Buscando paciente por ID - Servicio");
        Paciente paciente = pacienteRepositorio.findByDni(pacienteDni).orElseThrow(() ->
            ApiExcepcion.recursoNoEncontrado(String.format("El paciente de DNI: %s no existe", pacienteDni))
        );
        return pacienteMapper.pacienteADto(paciente);
    }

    @Override
    public PacienteDto agregar(PacienteDto datosPaciente) {
        log.debug("Agregando paciente - Servicio");
        String dniPaciente = datosPaciente.getDni();
        pacienteRepositorio.findByDni(dniPaciente).ifPresent(p -> {
            throw ApiExcepcion.conflictoPorViolacionDeEstado(String.format("Ya existe un paciente con el DNI: %s", dniPaciente));
        });

        Paciente paciente = pacienteRepositorio.save(pacienteMapper.dtoAPaciente(datosPaciente));
        return pacienteMapper.pacienteADto(paciente);
    }

    @Override
    public PacienteDto modificar(Long pacienteId, PacienteDto datosPaciente) {
        log.debug("Modificando paciente por ID - Servicio");
        Paciente paciente = pacienteRepositorio.findById(pacienteId).orElseThrow(() ->
            ApiExcepcion.recursoNoEncontrado(String.format("El paciente de ID: %d no existe", pacienteId))
        );

        pacienteMapper.actualizarPacienteDesdeDto(paciente, datosPaciente);
        return pacienteMapper.pacienteADto(pacienteRepositorio.save(paciente));
    }

    @Transactional
    @Override
    public void eliminar(Long pacienteId) {
        log.debug("Eliminando paciente por ID - Servicio");
        turnoRepositorio.liberarTurnosPorPacienteId(pacienteId);
        pacienteRepositorio.deleteById(pacienteId);
    }
}
