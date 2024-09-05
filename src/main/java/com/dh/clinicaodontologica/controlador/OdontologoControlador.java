package com.dh.clinicaodontologica.controlador;


import com.dh.clinicaodontologica.modelo.Odontologo;
import com.dh.clinicaodontologica.servicio.IOdontologoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/odontologos")
public class OdontologoControlador {
    @Autowired
    private IOdontologoServicio odontologoServicio;

    @GetMapping
    public ResponseEntity<List<Odontologo>> listar(){
        return ResponseEntity.ok(odontologoServicio.listar());
    }

    @GetMapping("/{odontologoId}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long odontologoId){
        if (odontologoId == null) return ResponseEntity.badRequest().build();

        Odontologo odontologo = odontologoServicio.buscar(odontologoId);

        if (odontologo == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(odontologo);
    }

    @PostMapping
    public ResponseEntity<Odontologo> agregar(@RequestBody Odontologo odontologo){
        Odontologo odontologoAgregado = odontologoServicio.agregar(odontologo);
        return ResponseEntity.created(URI.create("/api/v1/odontologos/" + odontologoAgregado.getId())).body(odontologoAgregado);
    }

    @PutMapping("/{odontologoId}")
    public ResponseEntity<Odontologo> modificar(@PathVariable Long odontologoId, @RequestBody Odontologo odontologo){
        if (odontologoId == null) return ResponseEntity.badRequest().build();

        odontologo.setId(odontologoId);
        return ResponseEntity.ok(odontologoServicio.modificar(odontologo));
    }

    @DeleteMapping("/{odontologoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long odontologoId){
        if (odontologoId == null) return ResponseEntity.badRequest().build();

        odontologoServicio.eliminar(odontologoId);
        return ResponseEntity.noContent().build();
    }
}
