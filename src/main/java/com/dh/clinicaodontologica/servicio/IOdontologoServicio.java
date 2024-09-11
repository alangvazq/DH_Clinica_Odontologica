package com.dh.clinicaodontologica.servicio;

import com.dh.clinicaodontologica.modelo.Odontologo;
import com.dh.clinicaodontologica.modelo.dto.OdontologoDto;

import java.util.List;

public interface IOdontologoServicio {
    List<OdontologoDto> listar();
    OdontologoDto buscar(Long odontologoId);
    OdontologoDto agregar(OdontologoDto datosOdontologo);
    OdontologoDto modificar(Long odontologoId, OdontologoDto datosOdontologo);
    void eliminar(Long odontologoId);
}
