package com.dh.clinicaodontologica.dao.impl;

import com.dh.clinicaodontologica.dao.BDUtilidades;
import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.modelo.Domicilio;
import com.dh.clinicaodontologica.modelo.Paciente;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PacienteDaoH2Test {
    private static final Logger LOGGER = Logger.getLogger(PacienteDaoH2Test.class);

    @BeforeEach
    void setUpConnection() throws Exception {
        BDUtilidades.limpiarBaseDatos();
    }

    @Test
    void testListarPacientes() throws Exception {
        IDao<Paciente, Long> pacienteDao = new PacienteDaoH2(new DomicilioDaoH2());

        Paciente paciente1 = pacienteDao.agregar(
                new Paciente(
                        "Juan",
                        "Perez",
                        new Domicilio("Calle Falsa", "123", "Springfield", "IL"),
                        "12345678",
                        LocalDate.now()
                ));

        Paciente paciente2 = pacienteDao.agregar(
                new Paciente(
                        "Maria",
                        "Lopez",
                        new Domicilio("Avenida Siempre Viva", "742", "Springfield", "IL"),
                        "87654321",
                        LocalDate.now()
                ));

        List<Paciente> pacientes = pacienteDao.listar();

        for (Paciente paciente : pacientes) {
            LOGGER.debug("Paciente en lista: " + paciente);
        }

        assertNotNull(pacientes, "La lista de pacientes no debería ser nula");
        assertEquals(2, pacientes.size(), "La cantidad de pacientes en la lista debería ser 2");
        assertTrue(pacientes.contains(paciente1), "La lista debería contener el paciente1");
        assertTrue(pacientes.contains(paciente2), "La lista debería contener el paciente2");
    }

    @Test
    void testBuscarPaciente() throws Exception {
        IDao<Paciente, Long> pacienteDao = new PacienteDaoH2(new DomicilioDaoH2());

        Paciente pacienteEsperado = pacienteDao.agregar(
                new Paciente(
                        "Juan",
                        "Perez",
                        new Domicilio("Calle Falsa", "123", "Springfield", "IL"),
                        "12345678",
                        LocalDate.now()
                ));

        Paciente pacienteGuardado = pacienteDao.buscar(pacienteEsperado.getId()).orElse(null);

        assertNotNull(pacienteGuardado, "El paciente recuperado no debe ser nulo");
        assertEquals(pacienteEsperado.getId(), pacienteGuardado.getId(), "El id del paciente debe coincidir");
        assertEquals(pacienteEsperado.getNombre(), pacienteGuardado.getNombre(), "El nombre del paciente debe coincidir");
        assertEquals(pacienteEsperado.getApellido(), pacienteGuardado.getApellido(), "El apellido del paciente debe coincidir");
        assertEquals(pacienteEsperado.getDomicilio().getId(), pacienteGuardado.getDomicilio().getId(), "El id del domicilio debe coincidir");
        assertEquals(pacienteEsperado.getDni(), pacienteGuardado.getDni(), "El DNI del paciente debe coincidir");
        assertEquals(pacienteEsperado.getFechaAlta(), pacienteGuardado.getFechaAlta(), "La fecha de alta del paciente debe coincidir");
    }

    @Test
    void testAgregarPaciente() throws Exception {
        IDao<Paciente, Long> pacienteDao = new PacienteDaoH2(new DomicilioDaoH2());

        Paciente pacienteEsperado = new Paciente(
                "Juan",
                "Perez",
                new Domicilio("Calle Falsa", "123", "Springfield", "IL"),
                "12345678",
                LocalDate.now()
            );

        Paciente pacienteGuardado = pacienteDao.agregar(pacienteEsperado);

        assertNotNull(pacienteGuardado.getId(), "El id del paciente no debe ser nulo");
        assertEquals(pacienteGuardado.getNombre(), pacienteEsperado.getNombre(), "El nombre del paciente debe coincidir");
        assertEquals(pacienteGuardado.getApellido(), pacienteEsperado.getApellido(), "El apellido del paciente debe coincidir");
        assertEquals(pacienteGuardado.getDomicilio().getId(), pacienteEsperado.getDomicilio().getId(), "El id del domicilio debe coincidir");
        assertEquals(pacienteGuardado.getDni(), pacienteEsperado.getDni(), "El DNI del paciente debe coincidir");
        assertEquals(pacienteGuardado.getFechaAlta(), pacienteEsperado.getFechaAlta(), "La fecha de alta del paciente debe coincidir");
    }

    @Test
    void testModificarPaciente() throws Exception {
        IDao<Paciente, Long> pacienteDao = new PacienteDaoH2(new DomicilioDaoH2());

        Paciente pacienteEsperado = pacienteDao.agregar(
                new Paciente(
                        "Juan",
                        "Perez",
                        new Domicilio("Calle Falsa", "123", "Springfield", "IL"),
                        "12345678",
                        LocalDate.now()
                ));

        pacienteEsperado.setNombre("Juan Actualizado");
        pacienteEsperado.setApellido("Perez Actualizado");
        pacienteEsperado.setDni("87654321");
        pacienteEsperado.setFechaAlta(LocalDate.of(2023, 1, 1));

        Paciente pacienteGuardado = pacienteDao.modificar(pacienteEsperado);

        assertEquals(pacienteEsperado.getNombre(), pacienteGuardado.getNombre(), "El nombre del paciente actualizado debe coincidir");
        assertEquals(pacienteEsperado.getApellido(), pacienteGuardado.getApellido(), "El apellido del paciente actualizado debe coincidir");
        assertEquals(pacienteEsperado.getDni(), pacienteGuardado.getDni(), "El DNI del paciente actualizado debe coincidir");
        assertEquals(pacienteEsperado.getFechaAlta(), pacienteGuardado.getFechaAlta(), "La fecha de alta del paciente actualizado debe coincidir");
    }

    @Test
    void testEliminarPaciente() throws Exception {
        DomicilioDaoH2 domicilioDao = new DomicilioDaoH2();
        IDao<Paciente, Long> pacienteDao = new PacienteDaoH2(domicilioDao);

        Paciente paciente = pacienteDao.agregar(
                new Paciente(
                    "Juan",
                    "Perez",
                    new Domicilio("Calle Para Borrar", "789", "Springfield", "IL"),
                    "12345678",
                    LocalDate.now()
                ));

        pacienteDao.eliminar(paciente.getId());

        Optional<Paciente> pacienteEncontrado = pacienteDao.buscar(paciente.getId());
        Optional<Domicilio> domicilioEncontrado = domicilioDao.buscar(paciente.getDomicilio().getId());

        assertTrue(pacienteEncontrado.isEmpty(), "El paciente encontrado debe ser nulo");
        assertTrue(domicilioEncontrado.isEmpty(), "El domicilio encontrado debe ser nulo");
    }
}
