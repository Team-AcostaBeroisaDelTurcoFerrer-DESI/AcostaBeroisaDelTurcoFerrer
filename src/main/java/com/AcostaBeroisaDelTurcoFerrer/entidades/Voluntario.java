package com.AcostaBeroisaDelTurcoFerrer.entidades;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Voluntario extends Persona {

    private String nroSeguro;

    // Constructores, getters y setters
    public Voluntario() {}

    public Voluntario(String apellido, String nombre, String direccion, String dni, LocalDate fechaNacimiento, String ocupacion, String nroSeguro) {
        super(apellido, nombre, direccion, dni, fechaNacimiento, ocupacion);
        this.nroSeguro = nroSeguro;
    }

    // Getters y Setters
    public String getNroSeguro() {
        return nroSeguro;
    }

    public void setNroSeguro(String nroSeguro) {
        this.nroSeguro = nroSeguro;
    }
}