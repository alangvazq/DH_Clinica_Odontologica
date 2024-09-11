package com.dh.clinicaodontologica.controlador;


import com.dh.clinicaodontologica.modelo.Odontologo;
import com.dh.clinicaodontologica.modelo.dto.OdontologoDto;
import com.dh.clinicaodontologica.servicio.IOdontologoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/odontologos")
@RequiredArgsConstructor
public class OdontologoControlador {
    private final IOdontologoServicio odontologoServicio;
    private static final Logger LOGGER = Logger.getLogger(PacienteControlador.class);

    @GetMapping
    public ResponseEntity<List<OdontologoDto>> listar(){
        LOGGER.debug("Listando odontólogos - Controlador");
        return ResponseEntity.ok(odontologoServicio.listar());
    }

    @GetMapping("/{odontologoId}")
    public ResponseEntity<OdontologoDto> buscarPorId(@PathVariable Long odontologoId){
        LOGGER.debug("Buscando odontólogo por id - Controlador");
        return ResponseEntity.ok(odontologoServicio.buscar(odontologoId));
    }

    @PostMapping
    public ResponseEntity<OdontologoDto> agregar(@Valid @RequestBody OdontologoDto odontologo){
        LOGGER.debug("Agregando odontólogo - Controlador");
        OdontologoDto odontologoRespuesta = odontologoServicio.agregar(odontologo);
        return ResponseEntity.created(URI.create("/api/v1/odontologos/" + odontologoRespuesta.getId())).body(odontologoRespuesta);
    }

    @PutMapping("/{odontologoId}")
    public ResponseEntity<OdontologoDto> modificar(@PathVariable Long odontologoId, @Valid @RequestBody OdontologoDto odontologo){
        LOGGER.debug("Modificando odontólogo - Controlador");
        return ResponseEntity.ok(odontologoServicio.modificar(odontologoId ,odontologo));
    }

    @DeleteMapping("/{odontologoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long odontologoId){
        LOGGER.debug("Eliminando odontólogo - Controlador");
        odontologoServicio.eliminar(odontologoId);
        return ResponseEntity.noContent().build();
    }
}
