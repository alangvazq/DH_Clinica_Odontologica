package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.modelo.dto.DomicilioDto;
import com.dh.clinicaodontologica.modelo.dto.OdontologoDto;
import com.dh.clinicaodontologica.modelo.dto.PacienteDto;
import com.dh.clinicaodontologica.modelo.dto.TurnoDto;
import com.dh.clinicaodontologica.servicio.IOdontologoServicio;
import com.dh.clinicaodontologica.servicio.IPacienteServicio;
import com.dh.clinicaodontologica.servicio.ITurnoServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServicioImplTest {
    @Autowired
    private ITurnoServicio turnoServicio;

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

    @Test
    @Transactional
    public void testListarTurnosExitosos(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());
        OdontologoDto odontologoGuardado = odontologoServicio.agregar(crearOdontologoDto());

        TurnoDto turnoDto = TurnoDto.builder()
                .fecha(LocalDate.now())
                .odontologo(odontologoGuardado)
                .paciente(pacienteGuardado).build();

        turnoServicio.agregar(turnoDto);
        List<TurnoDto> listadoTurnos = turnoServicio.listar();
        assertFalse(listadoTurnos.isEmpty());
    }

    @Test
    @Transactional
    public void testListarTurnosInexistentes(){
        List<TurnoDto> listadoTurnos = turnoServicio.listar();
        assertTrue(listadoTurnos.isEmpty());
    }

    @Test
    @Transactional
    public void testBuscarTurnoExistente(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());
        OdontologoDto odontologoGuardado = odontologoServicio.agregar(crearOdontologoDto());

        TurnoDto turnoDto = TurnoDto.builder()
                .fecha(LocalDate.now())
                .odontologo(odontologoGuardado)
                .paciente(pacienteGuardado).build();

        TurnoDto turnoGuardado = turnoServicio.agregar(turnoDto);

        TurnoDto turnoEncontrado = turnoServicio.buscar(turnoGuardado.getId());
        assertNotNull(turnoEncontrado);
    }

    @Test
    @Transactional
    public void testBuscarTurnoInexistente(){
        assertThrows(Exception.class, () -> turnoServicio.buscar(1L));
    }

    @Test
    @Transactional
    public void testAgregarTurnoExitoso(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());
        OdontologoDto odontologoGuardado = odontologoServicio.agregar(crearOdontologoDto());

        TurnoDto turnoDto = TurnoDto.builder()
                .fecha(LocalDate.now())
                .odontologo(odontologoGuardado)
                .paciente(pacienteGuardado).build();

        TurnoDto turnoGuardado = turnoServicio.agregar(turnoDto);
        assertNotNull(turnoGuardado);
    }

    @Test
    @Transactional
    public void testAgregarTurnoOdontologoNoEncontrado(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());

        TurnoDto turnoDto = TurnoDto.builder()
                .fecha(LocalDate.now())
                .paciente(pacienteGuardado).build();

        assertThrows(Exception.class, () -> turnoServicio.agregar(turnoDto));
    }

    @Test
    @Transactional
    public void testAgregarTurnoPacienteNoEncontrado(){
        OdontologoDto odontologoGuardado = odontologoServicio.agregar(crearOdontologoDto());

        TurnoDto turnoDto = TurnoDto.builder()
                .fecha(LocalDate.now())
                .odontologo(odontologoGuardado).build();

        assertThrows(Exception.class, () -> turnoServicio.agregar(turnoDto));
    }

    @Test
    @Transactional
    public void testAgregarTurnoOdontologoYpacienteNoEncontrados(){
        TurnoDto turnoDto = TurnoDto.builder()
                .fecha(LocalDate.now()).build();

        assertThrows(Exception.class, () -> turnoServicio.agregar(turnoDto));
    }

    @Test
    @Transactional
    public void testModificarTurnoExitoso(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());
        OdontologoDto odontologoGuardado = odontologoServicio.agregar(crearOdontologoDto());

        TurnoDto turnoDto = TurnoDto.builder()
                .fecha(LocalDate.now())
                .odontologo(odontologoGuardado)
                .paciente(pacienteGuardado).build();

        TurnoDto turnoGuardado = turnoServicio.agregar(turnoDto);

        TurnoDto turnoModificado = TurnoDto.builder()
                .fecha(LocalDate.now().plusDays(1))
                .odontologo(odontologoGuardado)
                .paciente(pacienteGuardado).build();

        TurnoDto turnoActualizado = turnoServicio.modificar(turnoGuardado.getId(), turnoModificado);
        assertEquals(turnoModificado.getFecha(), turnoActualizado.getFecha());
    }

    @Test
    @Transactional
    public void testModificarTurnoNoEncontrado(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());
        OdontologoDto odontologoGuardado = odontologoServicio.agregar(crearOdontologoDto());
        TurnoDto turnoModificado = TurnoDto.builder()
                .fecha(LocalDate.now().plusDays(1))
                .odontologo(odontologoGuardado)
                .paciente(pacienteGuardado).build();
        assertThrows(Exception.class, () -> turnoServicio.modificar(1L, turnoModificado));
    }

    @Test
    @Transactional
    public void testEliminarTurnoExitoso(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());
        OdontologoDto odontologoGuardado = odontologoServicio.agregar(crearOdontologoDto());

        TurnoDto turnoDto = TurnoDto.builder()
                .fecha(LocalDate.now())
                .odontologo(odontologoGuardado)
                .paciente(pacienteGuardado).build();

        TurnoDto turnoGuardado = turnoServicio.agregar(turnoDto);
        turnoServicio.eliminar(turnoGuardado.getId());
        assertThrows(Exception.class, () -> turnoServicio.buscar(turnoGuardado.getId()));
    }

    @Test
    @Transactional
    public void testEliminarTurnoNoEncontrado(){
        assertThrows(Exception.class, () -> turnoServicio.eliminar(1L));
    }
}