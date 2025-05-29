package com.AcostaBeroisaDelTurcoFerrer.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONDIMENTO") // Valor para la columna discriminadora cuando es un Condimento
public class Condimento extends Ingredientes {

    // Constructores (necesitarás el constructor por defecto y uno que llame al super)
    public Condimento() {
        super();
    }

    public Condimento(String nombre, int calorias) {
        super(nombre, calorias);
    }
    
}
