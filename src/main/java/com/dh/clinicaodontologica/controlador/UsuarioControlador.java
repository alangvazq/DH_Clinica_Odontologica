package com.dh.clinicaodontologica.controlador;

import com.dh.clinicaodontologica.modelo.Usuario;
import com.dh.clinicaodontologica.servicio.UsuarioServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
    private final UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioServicio.listarUsuarios();
    }

    @GetMapping("/{username}")
    public Usuario encontrarUsuarioPorUsername(@PathVariable String username) {
        System.out.println(username);
        return usuarioServicio.encontrarPorUsername(username);
    }

    @PostMapping
   // public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
   public ResponseEntity crearUsuario(@RequestBody Usuario usuario, UriComponentsBuilder uriComponentsBuilder) {
        // public Usuario crearUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCreado = usuarioServicio.crearUsuario(usuario);
        // URI url = uriComponentsBuilder.path("/usuarios/" + usuario.getUsername()).build().toUri();
        URI url2 = uriComponentsBuilder.path("/usuarios/{username}").buildAndExpand(usuario.getUsername()).toUri();
        // System.out.println(url);
        System.out.println(url2);
        // return ResponseEntity.ok().build();
        return ResponseEntity.created(url2).body(usuarioCreado);
        // return usuarioCreado;
        // return ResponseEntity.ok().body(usuarioCreado);
    }
}
