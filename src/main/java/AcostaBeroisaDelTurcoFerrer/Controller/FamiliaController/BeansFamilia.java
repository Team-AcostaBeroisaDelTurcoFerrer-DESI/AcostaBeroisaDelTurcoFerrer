package AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController;

import java.time.LocalDate;

import jakarta.validation.constraints.*;


public class BeansFamilia {
	
	@NotBlank(message = "El nombre de la familia no puede estar vacío.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    
    private String nombre;
    private LocalDate fechaRegistro;


    // Constructor vacío (necesario para Spring)
    public BeansFamilia() {
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
    
	

}
