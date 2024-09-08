package com.dh.clinicaodontologica.controlador;

import com.dh.clinicaodontologica.modelo.dto.PacienteDto;
import com.dh.clinicaodontologica.servicio.IPacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@RequiredArgsConstructor
public class PacienteControlador {
    private final IPacienteServicio pacienteServicio;
    private static final Logger log = Logger.getLogger(PacienteControlador.class);

    @GetMapping
    public ResponseEntity<List<PacienteDto>> listar() {
        log.debug("Listando pacientes - Controlador");
         return ResponseEntity.ok(pacienteServicio.listar());
    }

    @GetMapping("/{pacienteId}")
    public ResponseEntity<PacienteDto> buscarPorId(@PathVariable Long pacienteId) {
        log.debug("Buscando paciente por id - Controlador");
        return ResponseEntity.ok(pacienteServicio.buscar(pacienteId));
    }

    @PostMapping
    public ResponseEntity<PacienteDto> agregar(@Valid @RequestBody PacienteDto pacientePeticion) {
        log.debug("Agregando paciente - Controlador");
        PacienteDto pacienteRespuesta = pacienteServicio.agregar(pacientePeticion);
        return ResponseEntity.created(URI.create("/api/v1/pacientes/" + pacienteRespuesta.getId())).body(pacienteRespuesta);
    }

    @PutMapping("/{pacienteId}")
    public ResponseEntity<PacienteDto> modificar(@PathVariable Long pacienteId, @RequestBody PacienteDto datosPaciente) {
        log.debug("Modificando paciente - Controlador");
        return ResponseEntity.ok(pacienteServicio.modificar(pacienteId, datosPaciente));
    }

    @DeleteMapping("/{pacienteId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long pacienteId) {
        log.debug("Eliminando paciente - Controlador");
        pacienteServicio.eliminar(pacienteId);
        return ResponseEntity.noContent().build();
    }
}
