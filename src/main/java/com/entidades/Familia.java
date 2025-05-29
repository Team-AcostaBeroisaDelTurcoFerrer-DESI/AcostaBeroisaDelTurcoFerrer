package com.entidades;
import java.time.LocalDate;

import jakarta.persistence.Entity; // Importa de jakarta.persistence para Spring Boot 3+
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Marca esta clase como una entidad JPA
public class Familia {
	@Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generación de IDs
	private int Id;
	private LocalDate fechaRegistro;
	private String nombre;
	
	public Familia() {
		
	}
	public Familia(int id, LocalDate fechaRegistro, String nombre) {
		super();
		Id = id;
		this.fechaRegistro = fechaRegistro;
		this.nombre = nombre;
	}
	public Familia(LocalDate fechaRegistro, String nombre) {
		super();
		this.fechaRegistro = fechaRegistro;
		this.nombre = nombre;
	}		
	
}
