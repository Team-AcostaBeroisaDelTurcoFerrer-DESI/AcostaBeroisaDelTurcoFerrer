package com.AcostaBeroisaDelTurcoFerrer.entidades;
import java.time.LocalDate;

import jakarta.persistence.*; // Importa de jakarta.persistence para Spring Boot 3+

@Entity // Marca esta clase como una entidad JPA
public class EntregaAsistencia {
	@Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generación de IDs
	
	 // Relación OneToOne: Una EntregaAsistencia está asociada a una Preparacion
    // @JoinColumn define la clave foránea en la tabla entrega_asistencia
    // nullable = true permite que una entrega no tenga una preparación (si fuese el caso)
    @OneToOne(fetch = FetchType.LAZY) // Lazy loading es una buena práctica
    @JoinColumn(name = "preparacion_id", unique = true, nullable = true) // unique=true para 1:1
    private Preparacion preparacion;
	
	// Relación ManyToOne: Muchas EntregaAsistencia pueden pertenecer a una Familia
    // FetchType.LAZY para evitar carga innecesaria
    // @JoinColumn especifica la columna de clave foránea en la tabla entrega_asistencia
    // nullable = false indica que una EntregaAsistencia siempre debe tener una Familia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familia_nro_familia", nullable = false) // Nombre de la FK en la tabla entrega_asistencia
    private Familia familia;
    
 // Relación ManyToOne: Muchas EntregaAsistencia pueden ser realizadas por un Voluntario
    // FetchType.LAZY para evitar carga innecesaria
    // @JoinColumn especifica la columna de clave foránea en la tabla entrega_asistencia
    // nullable = false indica que una EntregaAsistencia siempre debe tener un Voluntario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voluntario_id", nullable = false) // Nombre de la FK en la tabla entrega_asistencia
    private Voluntario voluntario;
    
	
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
	
	public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }
	
	public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
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
	
	public Preparacion getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(Preparacion preparacion) {
        this.preparacion = preparacion;
    }
    
    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }
	

}
