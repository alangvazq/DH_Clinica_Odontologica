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
class OdontologoServicioImplTest {
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
    void testListarOdontologosExistentes() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        List<OdontologoDto> listadoOdontologos = odontologoServicio.listar();
        assertEquals(1, listadoOdontologos.size());
    }

    @Test
    @DisplayName("Listar odontologos inexistentes")
    void testListarOdontologoInexistente() {
        List<OdontologoDto> listadoOdontologos = odontologoServicio.listar();
        assertTrue(listadoOdontologos.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("Buscar odontologo existente")
    void testBuscarOdontologoExistente() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        OdontologoDto odontologoEncontrado = odontologoServicio.buscar(odontologo.getId());
        assertNotNull(odontologoEncontrado);
    }

    @Test
    @DisplayName("Buscar odontologo inexistente")
    void testBuscarOdontologoInexistente() {
        assertThrows(ApiExcepcion.class, () -> odontologoServicio.buscar(-1L));
    }

    @Test
    @DisplayName("Agregar odontologo exitoso")
    void testAgregarOdontologoExitoso() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        assertTrue(odontologoRepositorio.findById(odontologo.getId()).isPresent());
    }

    @Test
    @Transactional
    @DisplayName("Agregar odontologo duplicado")
    void testAgregarOdontologoDuplicado() {
        OdontologoDto odontologo = crearOdontologoDto();
        odontologoServicio.agregar(odontologo);
        assertThrows(ApiExcepcion.class, () -> odontologoServicio.agregar(odontologoServicio.agregar(odontologo)));
    }

    @Test
    @DisplayName("Modificar odontologo existente")
    void testModificarOdontologoExistente() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        odontologo.setNombre("Morty");
        odontologoServicio.modificar(odontologo.getId(), odontologo);
        assertEquals("Morty", odontologoRepositorio.findById(odontologo.getId()).get().getNombre());
    }

    @Test
    @DisplayName("Modificar odontologo inexistente")
    void testModificarOdontologoInexistente() {
        assertThrows(ApiExcepcion.class, () -> odontologoServicio.modificar(-1L, crearOdontologoDto()));
    }

    @Test
    @DisplayName("Eliminar odontologo existente")
    void testEliminarOdontologoExistente() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        odontologoServicio.eliminar(odontologo.getId());
        assertThrows(ApiExcepcion.class, () -> odontologoServicio.buscar(odontologo.getId()));
    }

    @Test
    @DisplayName("Eliminar odontologo inexistente")
    void testEliminarOdontologoInexistente() {
        assertDoesNotThrow(() -> odontologoServicio.eliminar(-1L));
    }
}