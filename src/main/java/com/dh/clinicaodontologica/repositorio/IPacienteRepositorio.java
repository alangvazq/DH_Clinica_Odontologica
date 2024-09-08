package com.dh.clinicaodontologica.repositorio;

import com.dh.clinicaodontologica.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPacienteRepositorio extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByDni(String dni);
}
