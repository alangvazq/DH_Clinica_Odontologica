package com.dh.clinicaodontologica.dao.impl;

import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.modelo.Turno;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TurnoDaoList implements IDao<Turno, Long> {
    private List<Turno> turnos;

    @Override
    public List<Turno> listar() {
        return turnos;
    }

    @Override
    public Optional<Turno> buscar(Long id) {
        Turno turnoBuscado = null;

        for (Turno turno: turnos) {
            if(turno.getId().equals(id)) {
               turnoBuscado = turno;
               break;
            }
        }

        return Optional.ofNullable(turnoBuscado);
    }

    @Override
    public Turno agregar(Turno turno) {
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno modificar(Turno turno) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
