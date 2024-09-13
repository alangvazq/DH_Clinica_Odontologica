package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.excepcion.ApiExcepcion;
import com.dh.clinicaodontologica.modelo.dto.DomicilioDto;
import com.dh.clinicaodontologica.modelo.dto.OdontologoDto;
import com.dh.clinicaodontologica.modelo.dto.PacienteDto;
import com.dh.clinicaodontologica.modelo.dto.TurnoDto;
import com.dh.clinicaodontologica.repositorio.IOdontologoRepositorio;
import com.dh.clinicaodontologica.repositorio.IPacienteRepositorio;
import com.dh.clinicaodontologica.repositorio.ITurnoRepositorio;
import com.dh.clinicaodontologica.servicio.IOdontologoServicio;
import com.dh.clinicaodontologica.servicio.IPacienteServicio;
import com.dh.clinicaodontologica.servicio.ITurnoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServicioTest {
    @Autowired
    private ITurnoServicio turnoServicio;

    @Autowired
    private ITurnoRepositorio turnoRepositorio;

    @Autowired
    private IOdontologoRepositorio odontologoRepositorio;

    @Autowired
    private IPacienteRepositorio pacienteRepositorio;

    @Autowired
    private IOdontologoServicio odontologoServicio;

    @Autowired
    private IPacienteServicio pacienteServicio;

    private DomicilioDto crearDomicilioDto(){
        return DomicilioDto.builder()
                .calle("Calle")
                .numero(123)
                .localidad("Localidad")
                .provincia("provincia").build();
    }

    private PacienteDto crearPacienteDto(){
        return PacienteDto.builder()
                .nombre("Morty")
                .apellido("Smith")
                .dni("12345678")
                .fechaAlta(LocalDate.now())
                .domicilio(crearDomicilioDto()).build();
    }

    private OdontologoDto crearOdontologoDto(){
        return OdontologoDto.builder()
                .nombre("Rick")
                .apellido("Sanchez")
                .matricula("123456").build();
    }

    @BeforeEach
    void setUp() {
        turnoRepositorio.deleteAll();
        pacienteRepositorio.deleteAll();
        odontologoRepositorio.deleteAll();
    }

    @Test
    @DisplayName("Debería retornar una lista vacía si no hay turnos registrados")
    void test_listarTurnos_sinTurnos() {
        List<TurnoDto> turnos = turnoServicio.listar();
        assertTrue(turnos.isEmpty());
    }

    @Test
    @DisplayName("Debería retornar una lista con 3 turnos dado un odontólogo")
    void test_listarTurnos_con1Turno() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        turnoServicio.crear(odontologo.getId());
        List<TurnoDto> turnos = turnoServicio.listar();
        assertEquals(3, turnos.size());
    }

    @Test
    @DisplayName("Debería lanzar excepción si no hay turnos")
    void test_buscarUltimoTurno_sinTurnos() {
        assertThrows(ApiExcepcion.class, () -> turnoServicio.buscarUltimo());
    }

    @Test
    @DisplayName("Debería retornar el último turno creado al crear 2 turnos")
    void test_buscarUltimoTurno_con2Turnos() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        turnoServicio.crear(odontologo.getId());
        turnoServicio.crear(odontologo.getId());
        TurnoDto ultimoTurno = turnoServicio.buscarUltimo();
        assertNotNull(ultimoTurno);
    }

    @Test
    @DisplayName("Debería lanzar excepción si el odontólogo no existe")
    void test_crearTurno_odontologoNoExiste() {
        assertThrows(ApiExcepcion.class, () -> turnoServicio.crear(-1L));
    }

    @Test
    @DisplayName("Debería crear turnos con fechas posteriores o iguales a la fecha actual")
    void test_crearTurno_fechasValidas() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        List<TurnoDto> turnos = turnoServicio.crear(odontologo.getId());
        LocalDateTime now = LocalDateTime.now();
        assertTrue(turnos.stream().allMatch(turno -> !turno.getFechaHora().isBefore(now)));
    }

    @Test
    @DisplayName("Debería lanzar excepción si el paciente no existe al asignar turno")
    void test_asignarTurno_pacienteNoExiste() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        TurnoDto turno = turnoServicio.crear(odontologo.getId()).get(0);
        assertThrows(ApiExcepcion.class, () -> turnoServicio.asignar(turno.getId(), -1L));
    }

    @Test
    @DisplayName("Debería asignar un turno correctamente con datos válidos")
    void test_asignarTurno_datosValidos() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        PacienteDto paciente = pacienteServicio.agregar(crearPacienteDto());
        TurnoDto turno = turnoServicio.crear(odontologo.getId()).get(0);
        TurnoDto turnoAsignado = turnoServicio.asignar(turno.getId(), paciente.getId());
        assertNotNull(turnoAsignado.getPaciente());
    }

    @Test
    @DisplayName("Debería lanzar excepción si el turno no existe al asignar turno")
    void test_asignarTurno_turnoNoExiste() {
        assertThrows(ApiExcepcion.class, () -> turnoServicio.asignar(-1L, -1L));
    }
}