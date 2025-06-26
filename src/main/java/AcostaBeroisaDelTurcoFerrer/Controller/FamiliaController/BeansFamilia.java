package AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonFormat;
import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;


public class BeansFamilia {
	
	@NotBlank(message = "El nombre de la familia no puede estar vacío.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    private String nombre;

    @NotNull(message = "La fecha de registro no puede estar vacía.")
    @PastOrPresent(message = "La fecha de registro debe ser una fecha pasada o presente.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    @NotEmpty(message = "La lista de integrantes no puede estar vacía.")
    @Size(min = 1, message = "Debe haber al menos un integrante en la familia.")
    List<Asistido> asistidos = new ArrayList<>();
 
    public BeansFamilia() {
    }
    public BeansFamilia(String nombre, LocalDate fechaRegistro, List<Asistido> asistidos) {
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
        this.asistidos = asistidos;
    }

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
	public List<Asistido> getAsistidos() {
        return asistidos;
    }
     public void setAsistidos(List<Asistido> asistidos) {
        this.asistidos = asistidos;
    }

    public void addAsistidos(Asistido a) {
        this.asistidos.add(a);
        Familia familia = new Familia();
        familia= toFamilia();
        a.setFamilia(familia);
    }

    public Familia toFamilia() {              
      Familia familia = new Familia();
      familia.setNombre(this.nombre);
      familia.setFechaRegistro(this.fechaRegistro);
      
      familia.setAsistidos(this.asistidos.stream()                           
                              .collect(Collectors.toList()));
      return familia;
    
    }
    
}
