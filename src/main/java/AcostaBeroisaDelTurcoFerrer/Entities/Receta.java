package AcostaBeroisaDelTurcoFerrer.Entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private boolean active = true;
    public boolean isActive() {
        return active;
    }
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Preparacion> preparaciones = new ArrayList<>();

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemsReceta> itemsRecetas = new HashSet<>();

    public Receta() {
    }

    public Receta(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Preparacion> getPreparaciones() {
        return preparaciones;
    }

    public void setPreparaciones(List<Preparacion> preparaciones) {
        this.preparaciones = preparaciones;
    }

    public Set<ItemsReceta> getItemsRecetas() {
        return itemsRecetas;
    }

    public void setItemsRecetas(Set<ItemsReceta> itemsRecetas) {
        this.itemsRecetas = itemsRecetas;
    }

    // Métodos de asociación
    public void addPreparacion(Preparacion preparacion) {
        preparaciones.add(preparacion);
        preparacion.setReceta(this);
    }

    public void removePreparacion(Preparacion preparacion) {
        preparaciones.remove(preparacion);
        preparacion.setReceta(null);
    }

    public void addItemReceta(Ingredientes ingrediente, int cantidad) {
        ItemsReceta itemReceta = new ItemsReceta(this, ingrediente, cantidad);
        this.itemsRecetas.add(itemReceta);
        ingrediente.getItemsRecetas().add(itemReceta);
    }

    public void removeItemReceta(ItemsReceta itemReceta) {
        this.itemsRecetas.remove(itemReceta);
        itemReceta.getIngrediente().getItemsRecetas().remove(itemReceta);
        itemReceta.setReceta(null);
        itemReceta.setIngrediente(null);
    }

    @Override
    public String toString() {
        return "Receta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

	public void setActive(boolean active) {
		this.active = active;
	}
}