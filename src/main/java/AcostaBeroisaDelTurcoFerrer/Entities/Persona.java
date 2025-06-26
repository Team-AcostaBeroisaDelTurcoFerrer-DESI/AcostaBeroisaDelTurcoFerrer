package AcostaBeroisaDelTurcoFerrer.Entities;
import jakarta.persistence.*;
import jakarta.persistence.DiscriminatorColumn; 
import jakarta.persistence.DiscriminatorType;  
import java.time.LocalDate; 


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_persona", discriminatorType = DiscriminatorType.STRING) 
public abstract class Persona {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) 
private Long id;
private String apellido;
private String nombre;
private String direccion;
private Long dni;  
private LocalDate fechaNacimiento;
private String ocupacion;

public Persona() {}

public Persona(String apellido, String nombre, String direccion, Long dni, LocalDate fechaNacimiento, String ocupacion) {
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
