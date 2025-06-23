package AcostaBeroisaDelTurcoFerrer.Entities;
import java.time.LocalDate;

import jakarta.persistence.*; // Importa de jakarta.persistence para Spring Boot 3+

@Entity
public class EntregaAsistencia {
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
    @OneToOne(fetch = FetchType.LAZY) // Lazy loading es una buena práctica
    @JoinColumn(name = "preparacion_id", unique = true, nullable = false) 
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
