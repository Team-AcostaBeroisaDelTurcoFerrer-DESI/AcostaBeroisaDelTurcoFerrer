package com.AcostaBeroisaDelTurcoFerrer.entidades;
import jakarta.persistence.*;

@Entity
@Table(name = "items_receta") // Puedes especificar un nombre de tabla si no te gusta el por defecto
public class ItemsReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private int calorias; // Esta caloría puede ser calculada (cantidad * calorias_ingrediente)

    // Lado propietario de la relación ManyToOne con Receta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receta_id") // Columna de clave foránea en items_receta que referencia a Receta
    private Receta receta;

    // Lado propietario de la relación ManyToOne con Ingrediente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingrediente_id") // Columna de clave foránea en items_receta que referencia a Ingrediente
    private Ingredientes ingrediente;

    // Constructores
    public ItemsReceta() {}

    public ItemsReceta(Receta receta, Ingredientes ingrediente, int cantidad) {
        this.receta = receta;
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
        // Calcular calorias si es necesario, o setearla desde fuera
        this.calorias = cantidad * ingrediente.getCalorias(); // Ejemplo de cálculo
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Ingredientes getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingredientes ingrediente) {
        this.ingrediente = ingrediente;
    }
}
