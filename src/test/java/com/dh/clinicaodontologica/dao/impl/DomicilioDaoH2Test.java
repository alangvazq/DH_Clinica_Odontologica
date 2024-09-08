package com.dh.clinicaodontologica.dao.impl;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DomicilioDaoH2Test {
//    private static final Logger LOGGER = Logger.getLogger(DomicilioDaoH2Test.class);
//
//    @BeforeEach
//    void cleanUp() throws Exception {
//        BDUtilidades.limpiarBaseDatos();
//    }
//
//    @Test
//    void testListarDomicilios() throws Exception {
//        IDao<Domicilio, Long> domicilioDao = new DomicilioDaoH2();
//        Domicilio domicilio1 = domicilioDao.agregar(new Domicilio("Calle Falsa", "123", "Springfield", "IL"));
//        Domicilio domicilio2 = domicilioDao.agregar(new Domicilio("Avenida Siempre Viva", "742", "Springfield", "IL"));
//
//        List<Domicilio> domicilios = domicilioDao.listar();
//        for (Domicilio domicilio : domicilios) {
//            LOGGER.debug("Domicilio en lista: " + domicilio);
//        }
//
//        assertNotNull(domicilios, "La lista de domicilios no debería ser nula");
//        assertEquals(2, domicilios.size(), "La cantidad de domicilios en la lista debería ser 2");
//        assertTrue(domicilios.contains(domicilio1), "La lista debería contener el domicilio1");
//        assertTrue(domicilios.contains(domicilio2), "La lista debería contener el domicilio2");
//    }
//
//    @Test
//    void testBuscarDomicilio() throws Exception {
//        IDao<Domicilio, Long> domicilioDao = new DomicilioDaoH2();
//        Domicilio domicilioEsperado = domicilioDao.agregar(new Domicilio("Calle Falsa", "123", "Springfield", "IL"));
//        Domicilio domicilioGuardado = domicilioDao.buscar(domicilioEsperado.getId()).orElse(null);
//
//        assertNotNull(domicilioGuardado, "El domicilio recuperado no debe ser nulo");
//        assertEquals(domicilioEsperado.getId(), domicilioGuardado.getId(), "El id del domicilio debe coincidir");
//        assertEquals(domicilioEsperado.getCalle(), domicilioGuardado.getCalle(), "La calle del domicilio debe coincidir");
//        assertEquals(domicilioEsperado.getNumero(), domicilioGuardado.getNumero(), "El número del domicilio debe coincidir");
//        assertEquals(domicilioEsperado.getLocalidad(), domicilioGuardado.getLocalidad(), "La localidad del domicilio debe coincidir");
//        assertEquals(domicilioEsperado.getProvincia(), domicilioGuardado.getProvincia(), "La provincia del domicilio debe coincidir");
//    }
//
//    @Test
//    void testAgregarDomicilio() throws Exception {
//        Domicilio domicilioEsperado = new Domicilio("Calle Falsa", "123", "Springfield", "IL");
//        IDao<Domicilio, Long> domicilioDao = new DomicilioDaoH2();
//        Domicilio domicilioGuardado = domicilioDao.agregar(domicilioEsperado);
//
//        assertNotNull(domicilioGuardado.getId(), "El id del domicilio no debe ser nulo");
//        assertEquals(domicilioGuardado.getCalle(), domicilioEsperado.getCalle(), "La calle del domicilio debe coincidir");
//        assertEquals(domicilioGuardado.getNumero(), domicilioEsperado.getNumero(), "El número del domicilio debe coincidir");
//        assertEquals(domicilioGuardado.getLocalidad(), domicilioEsperado.getLocalidad(), "La localidad del domicilio debe coincidir");
//        assertEquals(domicilioGuardado.getProvincia(), domicilioEsperado.getProvincia(), "La provincia del domicilio debe coincidir");
//    }
//
//    @Test
//    void testModificarDomicilio() throws Exception {
//        IDao<Domicilio, Long> domicilioDao = new DomicilioDaoH2();
//        Domicilio domicilioEsperado = domicilioDao.agregar(new Domicilio("Calle Falsa", "123", "Springfield", "IL"));
//
//        domicilioEsperado.setCalle("Calle Actualizada");
//        domicilioEsperado.setNumero("456");
//        domicilioEsperado.setLocalidad("Shelbyville");
//        domicilioEsperado.setProvincia("IL");
//        Domicilio domicilioGuardado = domicilioDao.modificar(domicilioEsperado);
//
//        assertEquals(domicilioEsperado.getCalle(), domicilioGuardado.getCalle(), "La calle del domicilio actualizado debe coincidir");
//        assertEquals(domicilioEsperado.getNumero(), domicilioGuardado.getNumero(), "El número del domicilio actualizado debe coincidir");
//        assertEquals(domicilioEsperado.getLocalidad(), domicilioGuardado.getLocalidad(), "La localidad del domicilio actualizado debe coincidir");
//        assertEquals(domicilioEsperado.getProvincia(), domicilioGuardado.getProvincia(), "La provincia del domicilio actualizado debe coincidir");
//    }
//
//    @Test
//    void testEliminarDomicilio() throws Exception {
//        Domicilio domicilio = new Domicilio("Calle Para Borrar", "789", "Springfield", "IL");
//        IDao<Domicilio, Long> domicilioDao = new DomicilioDaoH2();
//        domicilio = domicilioDao.agregar(domicilio);
//
//        domicilioDao.eliminar(domicilio.getId());
//
//        Optional<Domicilio> domicilioEncontrado = domicilioDao.buscar(domicilio.getId());
//        assertTrue(domicilioEncontrado.isEmpty(), "El domicilio encontrado debe ser nulo");
//    }
}