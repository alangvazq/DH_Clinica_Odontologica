package com.dh.clinicaodontologica.repositorio;

import com.dh.clinicaodontologica.modelo.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomicilioRepositorio extends JpaRepository<Domicilio, Long> {
}
