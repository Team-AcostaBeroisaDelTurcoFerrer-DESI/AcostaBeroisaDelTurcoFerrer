package AcostaBeroisaDelTurcoFerrer.Entities;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Asistido extends Persona {		
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="nroFamilia", referencedColumnName = "nroFamilia", nullable = true) // Nombre de la FK en la tabla asistido        
    private Familia familia; 
    
    private LocalDate fechaRegistro;
    
    // Getters y Setters   
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;//debe setear la fecha del dia automaticamente
    }
    
    public Familia getFamilia() {
        return familia;
    }
    public void setFamilia(Familia familia) {
        this.familia = familia;
    }
//*********************************************************************************************
    public Asistido(String apellido, String nombre, String direccion, String dni, LocalDate fechaNacimiento, String ocupacion) {
        super(apellido, nombre, direccion, dni, fechaNacimiento, ocupacion);
        this.fechaRegistro = LocalDate.now();
    }

    public Asistido() {}
  
}
