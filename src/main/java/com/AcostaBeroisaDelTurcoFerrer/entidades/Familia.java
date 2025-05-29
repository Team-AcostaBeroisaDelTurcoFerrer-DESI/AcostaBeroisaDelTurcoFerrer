package com.AcostaBeroisaDelTurcoFerrer.entidades;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*; // Importa de jakarta.persistence para Spring Boot 3+


@Entity // Marca esta clase como una entidad JPA
public class Familia {
	@Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generación de IDs
	private int nrofamilia;
	
	// Relación OneToMany: Una Familia puede tener muchas EntregaAsistencia
    // 'mappedBy' indica que el lado propietario de la relación es 'familia' en EntregaAsistencia
    // FetchType.LAZY es una buena práctica para colecciones
    // CascadeType.ALL y orphanRemoval = true: Si eliminas una Familia, también se eliminan sus EntregasAsistencia asociadas
    // Esto implica que las EntregasAsistencia "pertenecen" a la Familia y no pueden existir sin ella
    // Si una EntregaAsistencia pudiera existir sin una Familia, quitarías orphanRemoval = true y CascadeType.ALL,
    // o al menos CascadeType.REMOVE.
    @OneToMany(mappedBy = "familia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EntregaAsistencia> entregasAsistencia = new HashSet<>(); // Inicializar para evitar NullPointerException

 // Relación OneToOne con Asistido
    // mappedBy indica que el lado propietario de la relación es 'familia' en Asistido
    // CascadeType.ALL: Operaciones de persistencia se propagarán a Asistido
    // optional = true: Una Familia puede existir sin un Asistido (o Asistido puede existir sin Familia, dependiendo de la lógica de negocio)
    // FetchType.LAZY: Carga perezosa para evitar cargar Asistido a menos que se acceda explícitamente.
    @OneToOne(mappedBy = "familia", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Asistido asistido;
	
	private LocalDate fechaRegistro;
	private String nombre;
	
	public Familia() {}
	
	public Familia(LocalDate fechaRegistro, String nombre) {
		super();
		this.fechaRegistro = fechaRegistro;
		this.nombre = nombre;
	}
	
	// Métodos de conveniencia para la relación con Asistido
    public void setAsistido(Asistido asistido) {
        if (asistido != null) {
            asistido.setFamilia(this);
        }
        this.asistido = asistido;
    }
		
	// Métodos de conveniencia para añadir y quitar entregas
    // Aseguran que ambos lados de la relación estén sincronizados
    public void addEntregaAsistencia(EntregaAsistencia entrega) {
        entregasAsistencia.add(entrega);
        entrega.setFamilia(this);
    }

    public void removeEntregaAsistencia(EntregaAsistencia entrega) {
        entregasAsistencia.remove(entrega);
        entrega.setFamilia(null); // Desvincula la entrega de esta familia
    }	
	
    
    public Asistido getAsistido() {
        return asistido;
    }
    
    public Set<EntregaAsistencia> getEntregasAsistencia() {
        return entregasAsistencia;
    }

    public void setEntregasAsistencia(Set<EntregaAsistencia> entregasAsistencia) {
        this.entregasAsistencia = entregasAsistencia;
    }

	public int getnrofamilia() {
		return nrofamilia;
	}

	public void setId(int nro_familia) {
		nrofamilia = nro_familia;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
