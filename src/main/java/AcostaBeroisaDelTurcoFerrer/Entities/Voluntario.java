package AcostaBeroisaDelTurcoFerrer.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("VOLUNTARIO")
public class Voluntario extends Persona {

    private String nroSeguro;
    
    
    public Voluntario() {}

    public Voluntario(String apellido, String nombre, String direccion, Long dni, LocalDate fechaNacimiento, String ocupacion, String nroSeguro) {
        super(apellido, nombre, direccion, dni, fechaNacimiento, ocupacion);
        this.nroSeguro = nroSeguro;
    }

    public String getNroSeguro() {
        return nroSeguro;
    }
    public void setNroSeguro(String nroSeguro) {
        this.nroSeguro = nroSeguro;
    }
}