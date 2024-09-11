package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.modelo.Domicilio;
import com.dh.clinicaodontologica.modelo.Odontologo;
import com.dh.clinicaodontologica.modelo.Paciente;
import com.dh.clinicaodontologica.modelo.Turno;
import com.dh.clinicaodontologica.modelo.dto.DomicilioDto;
import com.dh.clinicaodontologica.modelo.dto.PacienteDto;
import com.dh.clinicaodontologica.modelo.dto.TurnoDto;
import com.dh.clinicaodontologica.repositorio.IOdontologoRepositorio;
import com.dh.clinicaodontologica.repositorio.IPacienteRepositorio;
import com.dh.clinicaodontologica.servicio.IOdontologoServicio;
import com.dh.clinicaodontologica.servicio.IPacienteServicio;
import com.dh.clinicaodontologica.servicio.ITurnoServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

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
        DomicilioDto domicilioDto = new DomicilioDto();
        domicilioDto.setCalle("Calle Falsa");
        domicilioDto.setNumero(123);
        domicilioDto.setLocalidad("Springfield");
        domicilioDto.setProvincia("Springfield");
        return domicilioDto;
    }

    private PacienteDto crearPacienteDto(){
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setNombre("Homero");
        pacienteDto.setApellido("Simpson");
        pacienteDto.setDni("12345678");
        pacienteDto.setFechaAlta(LocalDate.now());
        pacienteDto.setDomicilio(crearDomicilioDto());
        return pacienteDto;
    }

    private Odontologo crearOdontologo(){
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Dr. Nick");
        odontologo.setApellido("Rivera");
        odontologo.setMatricula("1234");
        return odontologo;
    }

    @Test
    @Transactional
    public void testAgregarTurnoExitoso(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());
        Odontologo odontologoGuardado = odontologoServicio.agregar(crearOdontologo());

        TurnoDto turnoDto = new TurnoDto();
        turnoDto.setFecha(LocalDate.now());
        turnoDto.setOdontologo(odontologoGuardado);
        turnoDto.setPaciente(pacienteGuardado);

        TurnoDto turnoGuardado = turnoServicio.agregar(turnoDto);

        assertNotNull(turnoGuardado);
    }

    @Test
    @Transactional
    public void testAgregarTurnoOdontologoNoEncontrado(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());

        TurnoDto turnoDto = new TurnoDto();
        turnoDto.setFecha(LocalDate.now());
        turnoDto.setPaciente(pacienteGuardado);

        assertThrows(Exception.class, () -> turnoServicio.agregar(turnoDto));
    }

    @Test
    @Transactional
    public void testAgregarTurnoPacienteNoEncontrado(){
        Odontologo odontologoGuardado = odontologoServicio.agregar(crearOdontologo());

        TurnoDto turnoDto = new TurnoDto();
        turnoDto.setFecha(LocalDate.now());
        turnoDto.setOdontologo(odontologoGuardado);

        assertThrows(Exception.class, () -> turnoServicio.agregar(turnoDto));
    }

    @Test
    @Transactional
    public void testAgregarTurnoOdontologoYpacienteNoEncontrados(){
        TurnoDto turnoDto = new TurnoDto();
        turnoDto.setFecha(LocalDate.now());

        assertThrows(Exception.class, () -> turnoServicio.agregar(turnoDto));
    }

    @Test
    @Transactional
    public void testModificarTurnoExitoso(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());
        Odontologo odontologoGuardado = odontologoServicio.agregar(crearOdontologo());

        TurnoDto turnoDto = new TurnoDto();
        turnoDto.setFecha(LocalDate.now());
        turnoDto.setOdontologo(odontologoGuardado);
        turnoDto.setPaciente(pacienteGuardado);

        TurnoDto turnoGuardado = turnoServicio.agregar(turnoDto);

        TurnoDto turnoModificado = new TurnoDto();
        turnoModificado.setFecha(LocalDate.now().plusDays(1));
        turnoModificado.setOdontologo(odontologoGuardado);
        turnoModificado.setPaciente(pacienteGuardado);

        TurnoDto turnoModificadoGuardado = turnoServicio.modificar(turnoGuardado.getId(), turnoModificado);

        assertEquals(turnoModificado.getFecha(), turnoModificadoGuardado.getFecha());
    }

    @Test
    @Transactional
    public void testModificarTurnoOdontologoNoEncontrado(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());
        Odontologo odontologoGuardado = odontologoServicio.agregar(crearOdontologo());

        TurnoDto turnoDto = new TurnoDto();
        turnoDto.setFecha(LocalDate.now());
        turnoDto.setOdontologo(odontologoGuardado);
        turnoDto.setPaciente(pacienteGuardado);

        TurnoDto turnoGuardado = turnoServicio.agregar(turnoDto);

        TurnoDto turnoModificado = new TurnoDto();
        turnoModificado.setFecha(LocalDate.now().plusDays(1));
        turnoModificado.setOdontologo(odontologoGuardado);
        turnoModificado.setPaciente(pacienteGuardado);

        assertThrows(Exception.class, () -> turnoServicio.modificar(turnoGuardado.getId(), turnoModificado));
    }

    @Test
    @Transactional
    public void testEliminarTurnoExitoso(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());
        Odontologo odontologoGuardado = odontologoServicio.agregar(crearOdontologo());

        TurnoDto turnoDto = new TurnoDto();
        turnoDto.setFecha(LocalDate.now());
        turnoDto.setOdontologo(odontologoGuardado);
        turnoDto.setPaciente(pacienteGuardado);

        TurnoDto turnoGuardado = turnoServicio.agregar(turnoDto);

        turnoServicio.eliminar(turnoGuardado.getId());

        assertThrows(Exception.class, () -> turnoServicio.buscar(turnoGuardado.getId()));
    }

    @Test
    @Transactional
    public void testEliminarTurnoNoEncontrado(){
        assertThrows(Exception.class, () -> turnoServicio.eliminar(1L));
    }

    @Test
    @Transactional
    public void testBuscarTurnoExitoso(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());
        Odontologo odontologoGuardado = odontologoServicio.agregar(crearOdontologo());

        TurnoDto turnoDto = new TurnoDto();
        turnoDto.setFecha(LocalDate.now());
        turnoDto.setOdontologo(odontologoGuardado);
        turnoDto.setPaciente(pacienteGuardado);

        TurnoDto turnoGuardado = turnoServicio.agregar(turnoDto);

        TurnoDto turnoBuscado = turnoServicio.buscar(turnoGuardado.getId());

        assertNotNull(turnoBuscado);
    }

    @Test
    @Transactional
    public void testBuscarTurnoNoEncontrado(){
        assertThrows(Exception.class, () -> turnoServicio.buscar(1L));
    }

    @Test
    @Transactional
    public void testListarTurnosExitoso(){
        PacienteDto pacienteGuardado = pacienteServicio.agregar(crearPacienteDto());
        Odontologo odontologoGuardado = odontologoServicio.agregar(crearOdontologo());

        TurnoDto turnoDto = new TurnoDto();
        turnoDto.setFecha(LocalDate.now());
        turnoDto.setOdontologo(odontologoGuardado);
        turnoDto.setPaciente(pacienteGuardado);

        TurnoDto turnoGuardado = turnoServicio.agregar(turnoDto);

        assertNotNull(turnoServicio.listar());
    }
}