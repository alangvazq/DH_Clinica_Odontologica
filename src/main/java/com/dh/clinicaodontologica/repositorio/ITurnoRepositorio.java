package com.dh.clinicaodontologica.repositorio;

import com.dh.clinicaodontologica.modelo.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepositorio extends JpaRepository<Turno, Long> {
}
