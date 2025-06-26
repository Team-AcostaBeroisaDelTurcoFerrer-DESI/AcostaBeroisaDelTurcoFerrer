package AcostaBeroisaDelTurcoFerrer.Controller.AsistidoController;

import java.time.LocalDate;
import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import jakarta.validation.constraints.NotNull;

public class BeansAsistido extends BeansPersona {

private Familia familia;  
@NotNull(message = "La fecha de registro no puede ser nula.")   
private LocalDate fechaRegistro;
      
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

    public BeansAsistido(String apellido, String nombre, String direccion, Long dni, LocalDate fechaNacimiento, String ocupacion) {
        super(apellido, nombre, direccion, dni, fechaNacimiento, ocupacion);
        this.fechaRegistro = LocalDate.now();
    }
    public BeansAsistido() {}
    
    public Asistido toAsistido() {
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
