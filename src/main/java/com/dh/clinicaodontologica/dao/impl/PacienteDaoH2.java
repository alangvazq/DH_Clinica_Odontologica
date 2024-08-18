package com.dh.clinicaodontologica.dao.impl;

import com.dh.clinicaodontologica.dao.BDUtilidades;
import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.modelo.Domicilio;
import com.dh.clinicaodontologica.modelo.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteDaoH2 implements IDao<Paciente, Long> {
    private static final Logger LOGGER = Logger.getLogger(PacienteDaoH2.class);

    private DomicilioDaoH2 domicilioDao;

    public PacienteDaoH2(DomicilioDaoH2 domicilioDao) {
        this.domicilioDao = domicilioDao;
    }

    @Override
    public List<Paciente> listar() throws Exception {
        String SELECT_PACIENTES = "SELECT * FROM pacientes;";
        List<Paciente> pacientes = new ArrayList<>();

        try (Connection conexion = BDUtilidades.getConexion();
             PreparedStatement pstmt = conexion.prepareStatement(SELECT_PACIENTES);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        domicilioDao.buscar(rs.getLong("domicilio_id")).orElse(null),
                        rs.getString("dni"),
                        rs.getDate("fecha_alta").toLocalDate()
                );
                pacientes.add(paciente);
                LOGGER.info("Paciente encontrado: " + paciente);
            }
        } catch (Exception e) {
            LOGGER.error("Error listando pacientes", e);
            throw new RuntimeException(e);
        }
        return pacientes;
    }

    @Override
    public Optional<Paciente> buscar(Long id) throws Exception {
        String SELECT_PACIENTE = "SELECT * FROM pacientes WHERE id = ?;";
        Paciente paciente = null;
        try (
                Connection conn = BDUtilidades.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(SELECT_PACIENTE)
        ) {
            pstmt.setLong(1, id);
            try(ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    Domicilio domicilio = domicilioDao.buscar(rs.getLong("domicilio_id")).orElse(null);
                    paciente = new Paciente(
                            rs.getLong("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            domicilio,
                            rs.getString("dni"),
                            rs.getDate("fecha_alta").toLocalDate()
                    );
                    LOGGER.info("Paciente encontrado: " + paciente);
                }
            }
            return Optional.ofNullable(paciente);
        } catch (Exception e) {
            LOGGER.error("Error buscando paciente de id: " + id, e);
            throw e;
        }
    }

    @Override
    public Paciente agregar(Paciente paciente) throws Exception {
        String INSERT_PACIENTE = "INSERT INTO pacientes (nombre, apellido, domicilio_id, dni, fecha_alta) VALUES (?, ?, ?, ?, ?);";
        try (
                Connection conn = BDUtilidades.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(INSERT_PACIENTE, Statement.RETURN_GENERATED_KEYS)
        ) {
            Domicilio domicilio = domicilioDao.agregar(paciente.getDomicilio());

            pstmt.setString(1, paciente.getNombre());
            pstmt.setString(2, paciente.getApellido());
            pstmt.setLong(3, domicilio.getId());
            pstmt.setString(4, paciente.getDni());
            pstmt.setDate(5, Date.valueOf(paciente.getFechaAlta()));
            pstmt.execute();

            try(ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    paciente.setId(rs.getLong(1));
                    LOGGER.info("Paciente creado: " + paciente);
                }
            }
            return paciente;
        } catch (Exception e) {
            LOGGER.error("Error creando paciente: " + paciente, e);
            throw e;
        }
    }

    @Override
    public Paciente modificar(Paciente paciente) throws Exception {
        String UPDATE_PACIENTE = "UPDATE pacientes SET nombre = ?, apellido = ?, dni = ?, fecha_alta = ? WHERE id = ?;";
        try (
                Connection conn = BDUtilidades.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(UPDATE_PACIENTE)
        ) {
            domicilioDao.modificar(paciente.getDomicilio());

            pstmt.setString(1, paciente.getNombre());
            pstmt.setString(2, paciente.getApellido());
            pstmt.setString(3, paciente.getDni());
            pstmt.setDate(4, Date.valueOf(paciente.getFechaAlta()));
            pstmt.setLong(5, paciente.getId());
            pstmt.executeUpdate();

            LOGGER.info("Paciente modificado: " + paciente);
            return paciente;
        } catch (Exception e) {
            LOGGER.error("Error modificando paciente de id: " + paciente.getId(), e);
            throw e;
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        String DELETE_DOMICILIO = "DELETE FROM domicilios D WHERE D.id = (SELECT P.id FROM pacientes P WHERE id = ?);";
        String DELETE_PACIENTE = "DELETE FROM pacientes WHERE id = ?;";
        try (Connection connection = BDUtilidades.getConexion();
             PreparedStatement psDeleteDomicilio = connection.prepareStatement(DELETE_DOMICILIO);
             PreparedStatement psDeletePaciente = connection.prepareStatement(DELETE_PACIENTE)
        ) {
            connection.setAutoCommit(false);

            psDeleteDomicilio.setLong(1, id);
            psDeleteDomicilio.executeUpdate();

            psDeletePaciente.setLong(1, id);
            psDeletePaciente.executeUpdate();

            connection.setAutoCommit(true);
            LOGGER.info("Id de paciente eliminado: " + id);
        } catch (Exception e) {
            LOGGER.error("Error modificando paciente de id: " + id, e);
            throw e;
        }
    }
}
