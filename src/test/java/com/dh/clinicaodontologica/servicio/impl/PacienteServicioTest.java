package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.excepcion.ApiExcepcion;
import com.dh.clinicaodontologica.modelo.Domicilio;
import com.dh.clinicaodontologica.modelo.Paciente;
import com.dh.clinicaodontologica.modelo.dto.DomicilioDto;
import com.dh.clinicaodontologica.modelo.dto.PacienteDto;
import com.dh.clinicaodontologica.repositorio.IPacienteRepositorio;
import com.dh.clinicaodontologica.servicio.IPacienteServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServicioTest {
    @Autowired
    private IPacienteRepositorio pacienteRepositorio;

    @Autowired
    private IPacienteServicio pacienteServicio;

    private static final Long ID_INVALIDO = -1L;

    @BeforeEach
    void setUp() {
        pacienteRepositorio.deleteAll();
    }

    @Test
    @DisplayName("Debería retornar una lista vacía, si no hay pacientes registrados")
    void test_listarPaciente_sinPacientes() {
        List<PacienteDto> listadoPacientes = pacienteServicio.listar();
        assertTrue(listadoPacientes.isEmpty());
    }

    @Test
    @DisplayName("Debería retornar una lista con 1 paciente, si solo hay un paciente registrado")
    void test_listarPaciente_con1Paciente() {
        pacienteRepositorio.save(getPacienteValido());
        List<PacienteDto> listadoPacientes = pacienteServicio.listar();
        assertEquals(1, listadoPacientes.size());
    }

    @Test
    @DisplayName("Debería fallar al intentar encontrar un paciente, si el id es inválido")
    void test_buscarPaciente_pacienteInexistente() {
        assertThrows(ApiExcepcion.class, () -> pacienteServicio.buscarPorId(ID_INVALIDO));
    }

    @Test
    @DisplayName("Debería retornar un paciente no nulo, si el id es válido")
    void test_buscarPaciente_datosValidos() {
        Paciente paciente = pacienteRepositorio.save(getPacienteValido());
        assertNotNull(pacienteServicio.buscarPorId(paciente.getId()));
    }

    @Test
    @DisplayName("Debería registrar el paciente, si sus datos son válidos")
    void test_agregarPaciente_datosValidos() {
        PacienteDto datosPaciente = pacienteServicio.agregar(getPacienteDtoValido());
        assertTrue(pacienteRepositorio.findById(datosPaciente.getId()).isPresent());
    }

    @Test
    @DisplayName("Debería fallar al intentar registrar un paciente, si ya existe")
    void test_agregarPaciente_pacienteDuplicado() {
        PacienteDto datosPaciente = getPacienteDtoValido();
        pacienteServicio.agregar(datosPaciente);
        assertThrows(ApiExcepcion.class, () -> pacienteServicio.agregar(pacienteServicio.agregar(datosPaciente)));
    }

    @Test
    @DisplayName("Debería fallar al intentar modificar un paciente, si no existe")
    void test_modificarPaciente_pacienteInexistente() {
        assertThrows(ApiExcepcion.class, () -> pacienteServicio.modificar(ID_INVALIDO, getPacienteDtoValido()));
    }

    @Test
    @DisplayName("Debería modificar un paciente, si hace un cambio de dato personal válido")
    void test_modificarPaciente_datoPersonal() {
        PacienteDto datosPaciente = pacienteServicio.agregar(getPacienteDtoValido());
        datosPaciente.setNombre("Juan Modificado");
        PacienteDto datosPacienteModificado = pacienteServicio.modificar(datosPaciente.getId(), datosPaciente);
        assertEquals(datosPaciente.getNombre(), datosPacienteModificado.getNombre());
    }

    @Test
    @DisplayName("Debería manejar la eliminación de un paciente, sin lanzar excepciones, si el paciente no existe")
    void test_eliminarPaciente_pacienteInexistente() {
        assertDoesNotThrow(()-> pacienteServicio.eliminar(ID_INVALIDO));
    }

    @Test
    @DisplayName("Debería eliminar un paciente, si existe")
    void test_eliminarPaciente_pacienteExistente() {
        Long pacienteId = pacienteRepositorio.save(getPacienteValido()).getId();
        pacienteServicio.eliminar(pacienteId);
        assertFalse(pacienteRepositorio.existsById(pacienteId));
    }

    private PacienteDto getPacienteDtoValido() {
        return PacienteDto.builder()
                .nombre("Juan")
                .apellido("Perez")
                .dni("12345678")
                .fechaAlta(LocalDate.now())
                .domicilio(DomicilioDto.builder()
                        .calle("Falsa")
                        .numero(123)
                        .localidad("Engañosa")
                        .provincia("Incierta")
                        .build()
                ).build();
    }

    private Paciente getPacienteValido() {
        Paciente paciente = new Paciente();
        paciente.setNombre("Juan");
        paciente.setApellido("Perez");
        paciente.setDni("12345678");
        paciente.setFechaAlta(LocalDate.now());

        Domicilio domicilioPaciente = new Domicilio();
        domicilioPaciente.setCalle("Falsa");
        domicilioPaciente.setNumero(123);
        domicilioPaciente.setLocalidad("Engañosa");
        domicilioPaciente.setProvincia("Incierta");
        paciente.setDomicilio(domicilioPaciente);

        return paciente;
    }
}