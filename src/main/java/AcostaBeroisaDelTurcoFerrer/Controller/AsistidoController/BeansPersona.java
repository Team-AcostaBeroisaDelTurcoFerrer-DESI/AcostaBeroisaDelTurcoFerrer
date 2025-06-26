package AcostaBeroisaDelTurcoFerrer.Controller.AsistidoController;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public class BeansPersona {
    private Long id;

    @NotBlank(message = "El Apellido es obligatorio")
    private String apellido;
   @NotBlank(message = "El Nombre es obligatorio")
    private String nombre;

   @NotBlank(message = "La Dirección es obligatoria")
    private String direccion;
 
   @NotNull(message = "El DNI del integrante es un dato requerido.") 
   @Positive(message = "El DNI debe ser un valor numérico positivo.") 
    private Long dni;  

   @NotNull(message = "La fecha de nacimiento del integrante es un dato requerido.") 
   @PastOrPresent(message = "La fecha de nacimiento no puede ser posterior a la fecha actual.") 
    private LocalDate fechaNacimiento;
    
    @NotBlank(message = "La ocupación es obligatoria")
    private String ocupacion;

    public BeansPersona() {}

    public BeansPersona(String apellido, String nombre, String direccion, Long dni, LocalDate fechaNacimiento, String ocupacion) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.direccion = direccion;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.ocupacion = ocupacion;
    }

public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
   
}
