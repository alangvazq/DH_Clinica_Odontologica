package com.dh.clinicaodontologica.controlador;

import com.dh.clinicaodontologica.modelo.Paciente;
import com.dh.clinicaodontologica.servicio.IPacienteServicio;
import com.dh.clinicaodontologica.servicio.impl.PacienteServicioImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteControlador {
    private final IPacienteServicio pacienteServicio;

    public PacienteControlador() {
        this.pacienteServicio = new PacienteServicioImpl();
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        return ResponseEntity.ok(pacienteServicio.listar());
    }

    @GetMapping("/{pacienteId}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long pacienteId) {
        if(pacienteId == null) return ResponseEntity.badRequest().build();

        Paciente paciente = pacienteServicio.buscar(pacienteId);

        if(paciente == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(paciente);
    }

    @PostMapping
    public ResponseEntity<Paciente> agregar(@RequestBody Paciente paciente) {
        Paciente pacienteAgregado = pacienteServicio.agregar(paciente);
        return ResponseEntity.created(URI.create("/api/v1/pacientes/" + pacienteAgregado.getId())).body(pacienteAgregado);
    }

    @PutMapping("/{pacienteId}")
    public ResponseEntity<Paciente> modificar(@PathVariable Long pacienteId, @RequestBody Paciente paciente) {
        if(pacienteId == null) return ResponseEntity.badRequest().build();

        paciente.setId(pacienteId);
        return ResponseEntity.ok(pacienteServicio.modificar(paciente));
    }

    @DeleteMapping("/{pacienteId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long pacienteId) {
        if(pacienteId == null) return ResponseEntity.badRequest().build();

        pacienteServicio.eliminar(pacienteId);;
        return ResponseEntity.noContent().build();
    }
}
