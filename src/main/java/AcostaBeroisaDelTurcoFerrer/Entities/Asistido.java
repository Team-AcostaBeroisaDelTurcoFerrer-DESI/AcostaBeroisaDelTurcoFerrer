package AcostaBeroisaDelTurcoFerrer.Entities;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("ASISTIDO") 
public class Asistido extends Persona {	

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="nroFamilia", referencedColumnName = "nroFamilia", nullable = true) 
    private Familia familia;
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

    public Asistido(String apellido, String nombre, String direccion, Long dni, LocalDate fechaNacimiento, String ocupacion) {
        super(apellido, nombre, direccion, dni, fechaNacimiento, ocupacion);
        this.fechaRegistro = LocalDate.now();
    }

    public Asistido() {}
  
}
