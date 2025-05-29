package com.AcostaBeroisaDelTurcoFerrer;

import org.springframework.boot.SpringApplication;

public class TestAcostaBeroisaDelTurcoFerrerApplication {

	public static void main(String[] args) {
		SpringApplication.from(AcostaBeroisaDelTurcoFerrerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
