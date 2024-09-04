package com.dh.clinicaodontologica.repositorio;

import com.dh.clinicaodontologica.modelo.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepositorio extends JpaRepository<Odontologo, Long> {
}
