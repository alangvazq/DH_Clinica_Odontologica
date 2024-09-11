package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.excepcion.ApiExcepcion;
import com.dh.clinicaodontologica.modelo.dto.OdontologoDto;
import com.dh.clinicaodontologica.repositorio.IOdontologoRepositorio;
import com.dh.clinicaodontologica.servicio.IOdontologoServicio;
import jakarta.transaction.Transactional;
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

    private OdontologoDto crearOdontologoDto(){
        return OdontologoDto.builder()
                .nombre("Rick")
                .apellido("Sanchez")
                .matricula("1234").build();
    }

    @Test
    @Transactional
    void testListarOdontologosExistentes() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        List<OdontologoDto> listadoOdontologos = odontologoServicio.listar();
        assertEquals(1, listadoOdontologos.size());
    }

    @Test
    @Transactional
    void testListarOdontologoInexistente() {
        List<OdontologoDto> listadoOdontologos = odontologoServicio.listar();
        assertTrue(listadoOdontologos.isEmpty());
    }

    @Test
    @Transactional
    void testBuscarOdontologoExistente() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        OdontologoDto odontologoEncontrado = odontologoServicio.buscar(odontologo.getId());
        assertNotNull(odontologoEncontrado);
    }

    @Test
    @Transactional
    void testBuscarOdontologoInexistente() {
        assertThrows(ApiExcepcion.class, () -> odontologoServicio.buscar(-1L));
    }

    @Test
    @Transactional
    void testAgregarOdontologoExitoso() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        assertTrue(odontologoRepositorio.findById(odontologo.getId()).isPresent());
    }

    @Test
    @Transactional
    void testAgregarOdontologoDuplicado() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        assertThrows(ApiExcepcion.class, () -> odontologoServicio.agregar(odontologo));
    }

    @Test
    @Transactional
    void testModificarOdontologoExistente() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        odontologo.setNombre("Morty");
        odontologoServicio.modificar(odontologo.getId(), odontologo);
        assertEquals("Morty", odontologoRepositorio.findById(odontologo.getId()).get().getNombre());
    }

    @Test
    @Transactional
    void testModificarOdontologoInexistente() {
        assertThrows(ApiExcepcion.class, () -> odontologoServicio.modificar(-1L, crearOdontologoDto()));
    }

    @Test
    @Transactional
    void testEliminarOdontologoExistente() {
        OdontologoDto odontologo = odontologoServicio.agregar(crearOdontologoDto());
        odontologoServicio.eliminar(odontologo.getId());
        assertThrows(ApiExcepcion.class, () -> odontologoServicio.buscar(odontologo.getId()));
    }

    @Test
    @Transactional
    void testEliminarOdontologoInexistente() {
        assertDoesNotThrow(() -> odontologoServicio.eliminar(-1L));
    }
}