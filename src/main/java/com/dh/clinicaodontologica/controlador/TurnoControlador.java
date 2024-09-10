package com.dh.clinicaodontologica.controlador;

import com.dh.clinicaodontologica.modelo.Turno;
import com.dh.clinicaodontologica.modelo.dto.TurnoDto;
import com.dh.clinicaodontologica.servicio.ITurnoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos")
@RequiredArgsConstructor
public class TurnoControlador {
    private final ITurnoServicio turnoServicio;
    private static final Logger log = Logger.getLogger(TurnoControlador.class);

    @GetMapping
    public ResponseEntity<List<TurnoDto>> listar() {
        log.debug("Listando turnos - Controlador");
        return ResponseEntity.ok(turnoServicio.listar());
    }

    @GetMapping("/{turnoId}")
    public ResponseEntity<TurnoDto> buscarPorId(@PathVariable Long turnoId) {
        log.debug("Buscando turno por id - Controlador");
        return ResponseEntity.ok(turnoServicio.buscar(turnoId));
    }

    @PostMapping
    public ResponseEntity<TurnoDto> agregar(@Valid @RequestBody TurnoDto turnoPeticion) {
        log.debug("Agregando turno - Controlador");
        TurnoDto turnoRespuesta = turnoServicio.agregar(turnoPeticion);
        return ResponseEntity.created(URI.create("/api/v1/turnos/" + turnoRespuesta.getId())).body(turnoRespuesta);
    }

    @PutMapping("/{turnoId}")
    public ResponseEntity<TurnoDto> modificar(@PathVariable Long turnoId, @Valid @RequestBody TurnoDto datosTurno) {
        log.debug("Modificando turno - Controlador");
        return ResponseEntity.ok(turnoServicio.modificar(turnoId, datosTurno));
    }

    @DeleteMapping("/{turnoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long turnoId) {
        log.debug("Eliminando turno - Controlador");
        turnoServicio.eliminar(turnoId);
        return ResponseEntity.noContent().build();
    }
}
