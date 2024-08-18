package com.dh.clinicaodontologica;


import com.dh.clinicaodontologica.dao.BDUtilidades;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) throws Exception {
		BDUtilidades.limpiarBaseDatos();
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}
}
