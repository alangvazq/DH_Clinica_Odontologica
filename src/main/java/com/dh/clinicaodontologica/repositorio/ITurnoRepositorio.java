package com.dh.clinicaodontologica.repositorio;

import com.dh.clinicaodontologica.modelo.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITurnoRepositorio extends JpaRepository<Turno, Long> {

    @Query("SELECT t FROM Turno t WHERE t.paciente IS NULL AND t.fechaHora > CURRENT_TIMESTAMP")
    List<Turno> findTurnosSinAsignar();

    @Query("SELECT t FROM Turno t ORDER BY t.fechaHora DESC LIMIT 1")
    Optional<Turno> findUltimoTurno();

    @Modifying
    @Query("UPDATE Turno t SET t.paciente = NULL WHERE t.paciente.id = ?1")
    void liberarTurnosPorPacienteId(Long pacienteId);
}
