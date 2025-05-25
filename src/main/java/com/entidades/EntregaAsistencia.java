package com.entidades;
import java.time.LocalDate;

import jakarta.persistence.Entity; // Importa de jakarta.persistence para Spring Boot 3+
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Marca esta clase como una entidad JPA
public class EntregaAsistencia {
	@Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generación de IDs
	private Long Id;
	private LocalDate fecha;
	private int cantidadRaciones;
	
	public EntregaAsistencia() {		
	}
	public EntregaAsistencia(Long id, LocalDate fecha, int cantidadRaciones) {
		super();
		Id = id;
		this.fecha = fecha;
		this.cantidadRaciones = cantidadRaciones;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public int getCantidadRaciones() {
		return cantidadRaciones;
	}
	public void setCantidadRaciones(int cantidadRaciones) {
		this.cantidadRaciones = cantidadRaciones;
	}
	

}
