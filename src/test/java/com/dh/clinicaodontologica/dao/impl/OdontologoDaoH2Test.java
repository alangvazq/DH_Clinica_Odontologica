package com.dh.clinicaodontologica.dao.impl;

import com.dh.clinicaodontologica.dao.BDUtilidades;
import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.modelo.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class OdontologoDaoH2Test {
    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2Test.class);

    @BeforeEach
    void setUpConnection() throws Exception {
        BDUtilidades.limpiarBaseDatos();
    }

    @Test
    void testListarOdontologos() throws Exception {
        IDao<Odontologo, Long> odontologoDao = new OdontologoDaoH2();

        Odontologo odontologo1 = odontologoDao.agregar(
                new Odontologo(
                        "Juan",
                        "Perez",
                        "12345678"
                ));

        Odontologo odontologo2 = odontologoDao.agregar(
                new Odontologo(
                        "Maria",
                        "Lopez",
                        "87654321"
                ));

        List<Odontologo> odontologos = odontologoDao.listar();

        for (Odontologo odontologo : odontologos) {
            LOGGER.debug("Odontologo en lista: " + odontologo);
        }

        assertNotNull(odontologos, "La lista de odontologos no debería ser nula");
        assertEquals(2, odontologos.size(), "La cantidad de odontologos en la lista debería ser 2");
        assertTrue(odontologos.contains(odontologo1), "La lista debería contener el odontologo1");
        assertTrue(odontologos.contains(odontologo2), "La lista debería contener el odontologo2");
    }

    @Test
    void testBuscarOdontologo() throws Exception {
        IDao<Odontologo, Long> odontologoDao = new OdontologoDaoH2();

        Odontologo odontologo1 = odontologoDao.agregar(
                new Odontologo(
                        "Juan",
                        "Perez",
                        "12345678"
                ));

        Odontologo odontologo2 = odontologoDao.agregar(
                new Odontologo(
                        "Maria",
                        "Lopez",
                        "87654321"
                ));

        Odontologo odontologoEncontrado = odontologoDao.buscar(odontologo1.getId()).orElse(null);

        LOGGER.debug("Odontologo encontrado: " + odontologoEncontrado);

        assertNotNull(odontologoEncontrado, "El odontologo encontrado no debería ser nulo");
        assertEquals(odontologo1, odontologoEncontrado, "El odontologo encontrado debería ser el odontologo1");
        assertNotEquals(odontologo2, odontologoEncontrado, "El odontologo encontrado no debería ser el odontologo2");
    }

    @Test
    void testAgregarOdontologo() throws Exception {
        IDao<Odontologo, Long> odontologoDao = new OdontologoDaoH2();

        Odontologo odontologo = odontologoDao.agregar(
                new Odontologo(
                        "Juan",
                        "Perez",
                        "12345678"
                ));

        assertNotNull(odontologo, "El odontologo agregado no debería ser nulo");
        assertEquals(odontologo.getId(), odontologoDao.buscar(odontologo.getId()).orElse(null).getId(), "El id del odontologo debería coincidir");
    }

    @Test
    void testModificarOdontologo() throws Exception {
        IDao<Odontologo, Long> odontologoDao = new OdontologoDaoH2();

        Odontologo odontologo = odontologoDao.agregar(
                new Odontologo(
                        "Juan",
                        "Perez",
                        "12345678"
                ));

        odontologo.setNombre("Maria");
        odontologo.setApellido("Lopez");
        odontologo.setMatricula("87654321");

        Odontologo odontologoModificado = odontologoDao.modificar(odontologo);

        assertNotNull(odontologoModificado, "El odontologo modificado no debería ser nulo");
        assertEquals(odontologo.getId(), odontologoModificado.getId(), "El id del odontologo modificado debería coincidir");
        assertEquals(odontologo.getNombre(), odontologoModificado.getNombre(), "El nombre del odontologo modificado debería coincidir");
        assertEquals(odontologo.getApellido(), odontologoModificado.getApellido(), "El apellido del odontologo modificado debería coincidir");
        assertEquals(odontologo.getMatricula(), odontologoModificado.getMatricula(), "La matricula del odontologo modificado debería coincidir");
    }

    @Test
    void testEliminarOdontologo() throws Exception {
        IDao<Odontologo, Long> odontologoDao = new OdontologoDaoH2();

        Odontologo odontologo = odontologoDao.agregar(
                new Odontologo(
                        "Juan",
                        "Perez",
                        "12345678"
                ));

        odontologoDao.eliminar(odontologo.getId());

        Odontologo odontologoEliminado = odontologoDao.buscar(odontologo.getId()).orElse(null);

        assertNull(odontologoEliminado, "El odontologo eliminado debería ser nulo");
    }
}