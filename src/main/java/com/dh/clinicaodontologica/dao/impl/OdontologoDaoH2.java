package com.dh.clinicaodontologica.dao.impl;

import com.dh.clinicaodontologica.dao.BDUtilidades;
import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.modelo.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OdontologoDaoH2 implements IDao<Odontologo, Long> {
    private static final Logger LOGGER = Logger.getLogger(PacienteDaoH2.class);

    @Override
    public List<Odontologo> listar() {
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
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error listando pacientes", e);
            throw new RuntimeException(e.getMessage());
        }
        return odontologos;
    }

    @Override
    public Optional<Odontologo> buscar(Long id) {
        String SELECT_ODONTOLOGO = "SELECT * FROM odontologos WHERE id = ?;";
        Odontologo odontologo = null;
        try (
                Connection con = BDUtilidades.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SELECT_ODONTOLOGO)
        ) {
            pstmt.setLong(1, id);
            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    odontologo = new Odontologo(
                            rs.getLong("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("matricula")
                    );
                    LOGGER.info("Odontologo encontrado: " + odontologo);
                }
            }
            return Optional.ofNullable(odontologo);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error buscando odontologo", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Odontologo agregar(Odontologo odontologo) {
        String INSERT_ODONTOLOGO = "INSERT INTO odontologos (nombre, apellido, matricula) VALUES (?, ?, ?);";
        try (
                Connection con = BDUtilidades.getConexion();
                PreparedStatement pstmt = con.prepareStatement(INSERT_ODONTOLOGO)
        ) {
            pstmt.setString(1, odontologo.getNombre());
            pstmt.setString(2, odontologo.getApellido());
            pstmt.setString(3, odontologo.getMatricula());
            pstmt.executeUpdate();
            LOGGER.info("Odontologo agregado: " + odontologo);
            return odontologo;
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error agregando odontologo", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        String UPDATE_ODONTOLOGO = "UPDATE odontologos SET nombre = ?, apellido = ?, matricula = ? WHERE id = ?;";
        try (
                Connection con = BDUtilidades.getConexion();
                PreparedStatement pstmt = con.prepareStatement(UPDATE_ODONTOLOGO)
        ) {
            pstmt.setString(1, odontologo.getNombre());
            pstmt.setString(2, odontologo.getApellido());
            pstmt.setString(3, odontologo.getMatricula());
            pstmt.setLong(4, odontologo.getId());
            pstmt.executeUpdate();
            LOGGER.info("Odontologo modificado: " + odontologo);
            return odontologo;
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error modificando odontologo", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void eliminar(Long id) {
        String DELETE_ODONTOLOGO = "DELETE FROM odontologos WHERE id = ?;";
        try (
                Connection con = BDUtilidades.getConexion();
                PreparedStatement pstmt = con.prepareStatement(DELETE_ODONTOLOGO)
        ) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            LOGGER.info("Odontologo eliminado con id: " + id);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error eliminando odontologo", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
