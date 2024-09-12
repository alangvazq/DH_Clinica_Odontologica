package com.dh.clinicaodontologica.controlador;

import com.dh.clinicaodontologica.modelo.dto.TurnoDto;
import com.dh.clinicaodontologica.servicio.ITurnoServicio;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/ultimo")
    public ResponseEntity<TurnoDto> buscarUtimoTurno() {
        log.debug("Buscando último turno - Controlador");
        return ResponseEntity.ok(turnoServicio.buscarUltimo());
    }

    @PostMapping
    public ResponseEntity<List<TurnoDto>> crear(@RequestParam(name = "odontologo") Long odontologoId) {
        log.debug("Agregando turno - Controlador");
        return ResponseEntity.ok(turnoServicio.crear(odontologoId));
    }

    @PutMapping("/{turnoId}")
    public ResponseEntity<TurnoDto> asignarTurno(@PathVariable Long turnoId, @RequestParam(name = "paciente") Long pacienteId) {
        log.debug("Asignando turno - Controlador");
        return ResponseEntity.ok(turnoServicio.asignar(turnoId, pacienteId));
    }
}
