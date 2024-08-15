package com.dh.clinicaodontologica.repositorio;

import com.dh.clinicaodontologica.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT * FROM usuarios WHERE username = ?1", nativeQuery = true)
    Optional<Usuario> findByUsername(String username);
}
