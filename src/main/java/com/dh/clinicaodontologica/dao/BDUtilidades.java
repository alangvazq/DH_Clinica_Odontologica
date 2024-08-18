package com.dh.clinicaodontologica.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BDUtilidades {

    private static final String CONTROLADOR = "org.h2.Driver";
    private static final String URL = "jdbc:h2:./clinica_odontologica;INIT=RUNSCRIPT FROM 'schema.sql'";
    private static final String USUARIO = "sa";
    private static final String CONTRASENIA = "";

    private static String REINICIAR_BB = """
            TRUNCATE TABLE domicilios RESTART IDENTITY;
            TRUNCATE TABLE pacientes RESTART IDENTITY;
        """;

    public static Connection getConexion() throws Exception {
        Class.forName(CONTROLADOR);
        return DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
    }

    public static void limpiarBaseDatos() throws Exception {
        try (Connection conn = BDUtilidades.getConexion();
             Statement stmt = conn.createStatement()
        ) {
            stmt.executeUpdate(REINICIAR_BB);
        }
    }
}
