package AcostaBeroisaDelTurcoFerrer.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PRODUCTO") 
public class Producto extends Ingredientes {

	
    private int stockDisponible;
	
    private double precioActual;


    public Producto() {
        super();
    }

    public Producto(String nombre, int calorias, int stockDisponible, double precioActual) {
        super(nombre, calorias);
        this.stockDisponible = stockDisponible;
        this.precioActual = precioActual;
    }

    // Getters y Setters
    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }
}
