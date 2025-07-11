package AcostaBeroisaDelTurcoFerrer.Controller.AssistedController;

import java.time.LocalDate;
import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import jakarta.validation.constraints.NotNull;

public class BeansAsistidos extends BeansPersonas {
    
    @NotNull(message = "La fecha de registro no puede ser nula.")   
    private LocalDate fechaRegistro;
    private Familia familia;  
    private Long id;
      
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(LocalDate fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
    }
    
    public Familia getFamilia() {
        return familia;
    }
    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public BeansAsistidos(String apellido, String nombre, String direccion, Long dni, LocalDate fechaNacimiento, String ocupacion) {
        super(apellido, nombre, direccion, dni, fechaNacimiento, ocupacion);
        this.fechaRegistro = LocalDate.now();
    }
    public BeansAsistidos() {}
    
    public Asistido toAsistido(Asistido a) {
        Asistido asistido = new Asistido();
        asistido.setApellido(this.getApellido());
        asistido.setNombre(this.getNombre());
        asistido.setDireccion(this.getDireccion());
        asistido.setDni(this.getDni());
        asistido.setFechaNacimiento(this.getFechaNacimiento());
        asistido.setOcupacion(this.getOcupacion());
        asistido.setFamilia(this.familia);
        asistido.setFechaRegistro(this.fechaRegistro);
        return asistido;
    }

}
