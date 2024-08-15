package com.dh.clinicaodontologica.servicio;

import com.dh.clinicaodontologica.modelo.Usuario;
import com.dh.clinicaodontologica.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Usuario encontrarPorUsername(String username) {
        return usuarioRepositorio.findByUsername(username).get();
    }
}
// DATA TRANSFER OBJECT (DTOs)
// Postman, Insomnia
// Parece un usuario: id, username
// |    ^
// V    |
// Controlador
// Parece un usuario: id, username
// |    ^
// V    |
// Servidor
// Usuario id, username
// |    ^
// V    |
// Repositorio
// Usuario: username, password
