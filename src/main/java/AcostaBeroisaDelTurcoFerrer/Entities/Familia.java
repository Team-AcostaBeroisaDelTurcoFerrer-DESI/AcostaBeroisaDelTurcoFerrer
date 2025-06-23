package AcostaBeroisaDelTurcoFerrer.Entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*; // Importa de jakarta.persistence para Spring Boot 3+


@Entity 
public class Familia {
//*********************************************************************
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long nroFamilia; 
	    
	  
	    public Long getNroFamilia() {
	        return nroFamilia;
	    }
	    public void setNroFamilia(Long nroFamilia) {
	        this.nroFamilia = nroFamilia; 
	    }  
	
	private LocalDate fechaRegistro;
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	private String nombre;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
//*********************************************************************************
	    
    public Familia() {}
	
	public Familia(String nombre) {
		super();
		this.fechaRegistro = LocalDate.now();
		this.nombre = nombre;
	}
//***************************************************************************************	
    @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Asistido> asistido = new ArrayList<>();
     
    public List<Asistido> getAsistido() {
		return asistido;
	}
	public void setAsistido(List<Asistido> asistido) {
		 this.asistido = asistido;
	        // Asegúrate de establecer la relación bidireccional
	        if (asistido != null) {
	            for (Asistido a : asistido) {
	                a.setFamilia(this);
	            }
	        }
	}  
    
//***************************************************************************************  
    @OneToMany(mappedBy = "familia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EntregaAsistencia> entregasAsistencia = new HashSet<>(); 
    public void addEntregaAsistencia(EntregaAsistencia entrega) {
        entregasAsistencia.add(entrega);
        entrega.setFamilia(this);
    }
    public void removeEntregaAsistencia(EntregaAsistencia entrega) {
        entregasAsistencia.remove(entrega);
        entrega.setFamilia(null); // Desvincula la entrega de esta familia
    }	 
    public Set<EntregaAsistencia> getEntregasAsistencia() {
        return entregasAsistencia;
    }

    public void setEntregasAsistencia(Set<EntregaAsistencia> entregasAsistencia) {
        this.entregasAsistencia = entregasAsistencia;
    }
//*********************************************************************************************
	
    public void addAsistido(Asistido a) {
        this.asistido.add(a);
        a.setFamilia(this); // Establece la relación bidireccional
    }

    public void removeAsistido(Asistido a) {
        this.asistido.remove(a);
        a.setFamilia(null); // Quita la relación bidireccional
    }
	
	
}
