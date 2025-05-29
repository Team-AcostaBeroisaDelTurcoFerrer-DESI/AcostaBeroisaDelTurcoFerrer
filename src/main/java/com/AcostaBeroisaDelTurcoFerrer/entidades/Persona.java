package com.AcostaBeroisaDelTurcoFerrer.entidades;
import jakarta.persistence.*;// Importa de jakarta.persistence para Spring Boot 3+
import java.time.LocalDate; // O java.util.Date si lo prefieres, pero LocalDate es más moderno

// Clase base
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // O GenerationType.IDENTITY si tu DB lo soporta y quieres IDs autoincrementales
    private Long id;

    private String apellido;
    private String nombre;
    private String direccion;
    private String dni; // Considera si DNI debe ser String o Long, y si debe ser único
    private LocalDate fechaNacimiento;
    private String ocupacion;

    // Constructores, getters y setters
    public Persona() {}

    public Persona(String apellido, String nombre, String direccion, String dni, LocalDate fechaNacimiento, String ocupacion) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.direccion = direccion;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.ocupacion = ocupacion;
    }

    // Getters y Setters
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
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
