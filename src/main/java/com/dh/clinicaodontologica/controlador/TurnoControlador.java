package com.dh.clinicaodontologica.controlador;

import com.dh.clinicaodontologica.modelo.Turno;
import com.dh.clinicaodontologica.servicio.ITurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/turnos")
public class TurnoControlador {
    private final ITurnoServicio turnoServicio;

    @Autowired
    public TurnoControlador(ITurnoServicio turnoServicio) {
        this.turnoServicio = turnoServicio;
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoServicio.agregar(turno));
    }

    @GetMapping("/{turnoId}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        //TODO que pasa si el id del paciente o del odontologo
        //que recibe el turno no existen
        return ResponseEntity.ok(turnoServicio.buscar(id));
    }
}
