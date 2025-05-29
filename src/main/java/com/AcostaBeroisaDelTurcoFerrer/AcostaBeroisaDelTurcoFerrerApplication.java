package com.AcostaBeroisaDelTurcoFerrer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan; // Importa esta anotación

@SpringBootApplication
@EntityScan("com.AcostaBeroisaDelTurcoFerrer.entidades") // O el paquete donde estén tus entidades
public class AcostaBeroisaDelTurcoFerrerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcostaBeroisaDelTurcoFerrerApplication.class, args);
	}

}
