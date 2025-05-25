package com.entidades;
import jakarta.persistence.Entity; // Importa de jakarta.persistence para Spring Boot 3+
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity // Marca esta clase como una entidad JPA

public class Preparacion {
	@Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generación de IDs
	private Long Id;
	private LocalDate fechaCoccion;
	private int totalRacionesPreparadas;
	private int stockRacionesRestantes;
	
	public Preparacion() {	
	}
	
	public Preparacion(Long id, LocalDate fechaCoccion, int totalRacionesPreparadas, int stockRacionesRestantes) {
		super();
		Id = id;
		this.fechaCoccion = fechaCoccion;
		this.totalRacionesPreparadas = totalRacionesPreparadas;
		this.stockRacionesRestantes = stockRacionesRestantes;
	}
	
	public LocalDate getFechaCoccion() {
		return fechaCoccion;
	}
	public void setFechaCoccion(LocalDate fechaCoccion) {
		this.fechaCoccion = fechaCoccion;
	}
	public int getTotalRacionesPreparadas() {
		return totalRacionesPreparadas;
	}
	public void setTotalRacionesPreparadas(int totalRacionesPreparadas) {
		this.totalRacionesPreparadas = totalRacionesPreparadas;
	}
	public int getStockRacionesRestantes() {
		return stockRacionesRestantes;
	}
	public void setStockRacionesRestantes(int stockRacionesRestantes) {
		this.stockRacionesRestantes = stockRacionesRestantes;
	}
	
}
