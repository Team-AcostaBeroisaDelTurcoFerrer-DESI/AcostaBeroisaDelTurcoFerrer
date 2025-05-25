package com.entidades;

import jakarta.persistence.Entity; // Importa de jakarta.persistence para Spring Boot 3+
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Marca esta clase como una entidad JPA
public class Receta {
	@Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generación de IDs
    private Long id;
    private String nombre;
    private String descripcion;
    
    public Receta() {
	}
    
	public Receta(Long id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
	 @Override
	    public String toString() {
	        return "Product{" +
	               "id=" + id +
	               ", name='" + nombre + '\'' +
	               ", price=" + descripcion +
	               '}';
	    } 

}
