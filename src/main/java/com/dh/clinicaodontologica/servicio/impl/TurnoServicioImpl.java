package com.dh.clinicaodontologica.servicio.impl;

import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.modelo.Turno;
import com.dh.clinicaodontologica.servicio.ITurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoServicioImpl implements ITurnoServicio {

    private IDao<Turno, Long> turnoDao;

    @Autowired
    public TurnoServicioImpl (IDao<Turno, Long> turnoDao) {
        this.turnoDao = turnoDao;
    }

    @Override
    public List<Turno> listar() {
        return turnoDao.listar();
    }

    @Override
    public Turno buscar(Long id) {
        return turnoDao.buscar(id).orElse(null);
    }

    @Override
    public Turno agregar(Turno turno) {
        return turnoDao.agregar(turno);
    }
}
