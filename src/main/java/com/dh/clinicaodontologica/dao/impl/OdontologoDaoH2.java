package com.dh.clinicaodontologica.dao.impl;

import com.dh.clinicaodontologica.dao.BDUtilidades;
import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.modelo.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OdontologoDaoH2 implements IDao<Odontologo, Long> {
    private static final Logger LOGGER = Logger.getLogger(PacienteDaoH2.class);


    @Override
    public List<Odontologo> listar() throws Exception {
        String SELECT_ODONTOLOGOS = "SELECT * FROM odontologos;";
        List<Odontologo> odontologos = new ArrayList<>();

        try (Connection conexion = BDUtilidades.getConexion();
             PreparedStatement pstmt = conexion.prepareStatement(SELECT_ODONTOLOGOS);
             ResultSet rs = pstmt.executeQuery()){
            while (rs.next()){
                Odontologo odontologo = new Odontologo(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("matricula")
                );
                odontologos.add(odontologo);
                LOGGER.info("Paciente encontrado: " + odontologo);
            }
        } catch (Exception e) {
            LOGGER.error("Error listando pacientes", e);
            throw new RuntimeException(e);
        }
        return odontologos;
    }

    @Override
    public Optional<Odontologo> buscar(Long id) throws Exception {
        return Optional.empty();
    }

    @Override
    public Odontologo agregar(Odontologo odontologo) throws Exception {
        return null;
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) throws Exception {
        return null;
    }

    @Override
    public void eliminar(Long id) throws Exception {

    }
}
