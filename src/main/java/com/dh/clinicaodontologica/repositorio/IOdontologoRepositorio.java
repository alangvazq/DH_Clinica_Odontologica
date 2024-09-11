package com.dh.clinicaodontologica.repositorio;

import com.dh.clinicaodontologica.modelo.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOdontologoRepositorio extends JpaRepository<Odontologo, Long> {
    Optional<Odontologo> findByMatricula(String matricula);
}
