package AcostaBeroisaDelTurcoFerrer.Entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity 
public class Familia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)


	private Long nroFamilia;
	@NotBlank(message = "El nombre de la familia no puede estar vacío.")
    @Size(min = 2, max = 100, message = "El nombre de la familia debe tener entre 2 y 100 caracteres.")
    private String nombre;
    @NotNull(message = "La fecha de registro de la familia no puede ser nula.")
    private java.time.LocalDate fechaRegistro;

    @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL,fetch = FetchType.LAZY)	
    private List<Asistido> asistidos = new ArrayList<>();

    @OneToMany(mappedBy = "familia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EntregaAsistencia> entregasAsistencia = new HashSet<>(); 
    
    @NotNull
    private boolean estaActiva = true;

	public boolean isEstaActiva() {
		return estaActiva;
	}
	public void setEstaActiva(boolean estaActiva) {
		this.estaActiva = estaActiva;
	}
	public Long getNroFamilia() {
	    return nroFamilia;
	}
	public void setNroFamilia(Long nroFamilia) {	
	    this.nroFamilia = nroFamilia; 
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
	    
    public Familia() {
	this.fechaRegistro = LocalDate.now();
	}
	
	public Familia(String nombre) {
	this(); 
    this.nombre = nombre;
	}
	
    public List<Asistido> getAsistidos() {
		return asistidos;
	}
	public void setAsistidos(List<Asistido> asistidos) {
		 this.asistidos = asistidos;
	        if (asistidos != null) {
	            for (Asistido a : asistidos) {
	                a.setFamilia(this);
	            }
	        }
	} 
	public void addAsistidos(Asistido a) {
        this.asistidos.add(a);
        a.setFamilia(this); 
    }
	 public void removeAsistidos(Asistido a) {
        this.asistidos.remove(a);
        a.setFamilia(null); // Quita la relación bidireccional
    }

    public Set<EntregaAsistencia> getEntregasAsistencia() {
        return entregasAsistencia;
    }
    public void setEntregasAsistencia(Set<EntregaAsistencia> entregasAsistencia) {
        this.entregasAsistencia = entregasAsistencia;
    }
    public void addEntregaAsistencia(EntregaAsistencia entrega) {
        entregasAsistencia.add(entrega);
        entrega.setFamilia(this);
    }	 
    public void removeEntregaAsistencia(EntregaAsistencia entrega) {
        entregasAsistencia.remove(entrega);
        entrega.setFamilia(null); 
    }
	
		
}
