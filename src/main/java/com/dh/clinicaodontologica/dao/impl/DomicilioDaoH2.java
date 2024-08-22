package com.dh.clinicaodontologica.dao.impl;

import com.dh.clinicaodontologica.dao.BDUtilidades;
import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.modelo.Domicilio;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DomicilioDaoH2 implements IDao<Domicilio, Long> {
    private static final Logger LOGGER = Logger.getLogger(DomicilioDaoH2.class);

    @Override
    public List<Domicilio> listar() {
        String SELECT_DOMICILIOS = "SELECT * FROM domicilios;";
        List<Domicilio> domicilios = new ArrayList<>();

        try (Connection con = BDUtilidades.getConexion();
             PreparedStatement pstmt = con.prepareStatement(SELECT_DOMICILIOS);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Domicilio domicilio = new Domicilio(
                        rs.getLong("id"),
                        rs.getString("calle"),
                        rs.getString("numero"),
                        rs.getString("localidad"),
                        rs.getString("provincia")
                );
                domicilios.add(domicilio);
                LOGGER.info("Domicilio encontrado: " + domicilio);
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error listando domicilios", e);
            throw new RuntimeException(e.getMessage());
        }
        return domicilios;
    }

    @Override
    public Optional<Domicilio> buscar(Long id) {
        String SELECT_DOMICILIO = "SELECT * FROM domicilios WHERE id = ?;";
        Domicilio domicilio = null;
        try (
                Connection con = BDUtilidades.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SELECT_DOMICILIO)
        ) {
            pstmt.setLong(1, id);
            try(ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    domicilio = new Domicilio(
                            rs.getLong("id"),
                            rs.getString("calle"),
                            rs.getString("numero"),
                            rs.getString("localidad"),
                            rs.getString("provincia")
                    );
                    LOGGER.info("Domicilio encontrado: " + domicilio);
                }
            }
            return Optional.ofNullable(domicilio);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error buscando domicilio de id: " + id, e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Domicilio agregar(Domicilio domicilio) {
        String INSERT_DOMICILIO = "INSERT INTO domicilios (calle, numero, localidad, provincia) VALUES (?, ?, ?, ?);";
        try (
                Connection con = BDUtilidades.getConexion();
                PreparedStatement pstmt = con.prepareStatement(INSERT_DOMICILIO, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, domicilio.getCalle());
            pstmt.setString(2, domicilio.getNumero());
            pstmt.setString(3, domicilio.getLocalidad());
            pstmt.setString(4, domicilio.getProvincia());
            pstmt.execute();

            try(ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    domicilio.setId(rs.getLong(1));
                    LOGGER.info("Domicilio creado: " + domicilio);
                }
            }
            return domicilio;
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error creando domicilio: " + domicilio, e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Domicilio modificar(Domicilio domicilio) {
        String UPDATE_DOMICILIO = "UPDATE domicilios SET calle = ?, numero = ?, localidad = ?, provincia = ? WHERE id = ?;";
        try (
                Connection con = BDUtilidades.getConexion();
                PreparedStatement pstmt = con.prepareStatement(UPDATE_DOMICILIO)
        ) {
            pstmt.setString(1, domicilio.getCalle());
            pstmt.setString(2, domicilio.getNumero());
            pstmt.setString(3, domicilio.getLocalidad());
            pstmt.setString(4, domicilio.getProvincia());
            pstmt.setLong(5, domicilio.getId());
            pstmt.executeUpdate();

            LOGGER.info("Domicilio modificado: " + domicilio);

            return domicilio;
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error modificando domicilio de id: " + domicilio.getId(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void eliminar(Long id) {
        String DELETE_DOMICILIO = "DELETE FROM domicilios WHERE id = ?;";
        try (Connection con = BDUtilidades.getConexion();
             PreparedStatement pstmt = con.prepareStatement(DELETE_DOMICILIO)
        ) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            LOGGER.info("Id de domicilio eliminado: " + id);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Error modificando domicilio de id: " + id, e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
