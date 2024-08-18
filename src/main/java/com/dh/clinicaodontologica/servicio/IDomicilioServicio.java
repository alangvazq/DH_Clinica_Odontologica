package com.dh.clinicaodontologica.servicio;

import com.dh.clinicaodontologica.modelo.Domicilio;

import java.util.List;

public interface IDomicilioServicio {
    List<Domicilio> listar() throws Exception;
    Domicilio buscar(Long id) throws Exception;
    Domicilio agregar(Domicilio domicilio) throws Exception;
    Domicilio modificar(Domicilio domicilio) throws Exception;
    void eliminar(Long id) throws Exception;
}
