package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.excepcion.ApiExcepcion;
import com.dh.clinicaodontologica.modelo.dto.OdontologoDto;
import com.dh.clinicaodontologica.repositorio.IOdontologoRepositorio;
import com.dh.clinicaodontologica.servicio.IOdontologoServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServicioTest {
    @Autowired
    private IOdontologoRepositorio odontologoRepositorio;

    @Autowired
    private IOdontologoServicio odontologoServicio;

    @BeforeEach
    void setUp() {
        odontologoRepositorio.deleteAll();
    }

    private OdontologoDto crearOdontologoDto(){
        return OdontologoDto.builder()
                .nombre("Rick")
                .apellido("Sanchez")
                .matricula("1234").build();
    }

    @Test
    @DisplayName("Listar odontologos existentes")
    void test_listarOdontologos_odontologoExistentes() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        List<OdontologoDto> listadoOdontologos = odontologoServicio.listar();
        assertEquals(1, listadoOdontologos.size());
    }

    @Test
    @DisplayName("Listar odontologos inexistentes")
    void test_listarOdontologo_odontologosInexistente() {
        List<OdontologoDto> listadoOdontologos = odontologoServicio.listar();
        assertTrue(listadoOdontologos.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("Buscar odontologo existente")
    void test_buscarOdontologo_odontologoExistente() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        OdontologoDto odontologoEncontrado = odontologoServicio.buscar(odontologo.getId());
        assertNotNull(odontologoEncontrado);
    }

    @Test
    @DisplayName("Buscar odontologo inexistente")
    void test_buscarOdontologo_odontologoInexistente() {
        assertThrows(ApiExcepcion.class, () -> odontologoServicio.buscar(-1L));
    }

    @Test
    @DisplayName("Agregar odontologo exitoso")
    void test_agregarOdontologo_odontologoExitoso() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        assertTrue(odontologoRepositorio.findById(odontologo.getId()).isPresent());
    }

    @Test
    @Transactional
    @DisplayName("Agregar odontologo duplicado")
    void test_agregarOdontologo_odontologoDuplicado() {
        OdontologoDto odontologo = crearOdontologoDto();
        odontologoServicio.agregar(odontologo);
        assertThrows(ApiExcepcion.class, () -> odontologoServicio.agregar(odontologoServicio.agregar(odontologo)));
    }

    @Test
    @DisplayName("Modificar odontologo existente")
    void test_modificarOdontologo_odontologoExistente() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        odontologo.setNombre("Morty");
        odontologoServicio.modificar(odontologo.getId(), odontologo);
        assertEquals("Morty", odontologoRepositorio.findById(odontologo.getId()).get().getNombre());
    }

    @Test
    @DisplayName("Modificar odontologo inexistente")
    void test_modificarOdontologo_odontologoInexistente() {        assertThrows(ApiExcepcion.class, () -> odontologoServicio.modificar(-1L, crearOdontologoDto()));
    }

    @Test
    @DisplayName("Eliminar odontologo existente")
    void test_eliminarOdontologo_odontologoExistente() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        odontologoServicio.eliminar(odontologo.getId());
        assertThrows(ApiExcepcion.class, () -> odontologoServicio.buscar(odontologo.getId()));
    }

    @Test
    @DisplayName("Eliminar odontologo inexistente")
    void test_eliminarOdontologo_odontologoInexistente() {
        assertDoesNotThrow(() -> odontologoServicio.eliminar(-1L));
    }
}