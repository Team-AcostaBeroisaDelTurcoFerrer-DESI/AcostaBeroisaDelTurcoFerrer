package com.AcostaBeroisaDelTurcoFerrer.entidades;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Asistido extends Persona {
	
	// Relación OneToOne con Familia
    // @JoinColumn: Especifica la clave foránea que referencia a Familia
    // nullable = true: Un Asistido puede no estar asociado a una Familia (o viceversa)
    // @MapsId: Indica que el ID de esta entidad (Asistido) es también el ID de la entidad Familia.
    // Esto significa que el ID de Asistido será el mismo nroFamilia al que está asociado.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nro_familia", referencedColumnName = "nroFamilia", nullable = true) // Nombre de la FK en la tabla asistido
    //@MapsId // Mapea el ID de Asistido con el ID de Familia
    private Familia familia;

    private LocalDate fechaRegistro;

    // Constructores, getters y setters
    public Asistido() {}

    public Asistido(String apellido, String nombre, String direccion, String dni, LocalDate fechaNacimiento, String ocupacion, LocalDate fechaRegistro) {
        super(apellido, nombre, direccion, dni, fechaNacimiento, ocupacion);
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
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
    
}
