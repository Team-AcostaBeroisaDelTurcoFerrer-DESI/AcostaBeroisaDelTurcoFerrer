package AcostaBeroisaDelTurcoFerrer.Entities;

import jakarta.persistence.*; 
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Entity 
public class Receta {
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    private Long id;	
    private String nombre;
    private String descripcion;
    
 
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Preparacion> preparaciones = new ArrayList<>(); 

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemsReceta> itemsRecetas = new HashSet<>(); 
    public Receta() {
	}
    
	public Receta(Long id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
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
	        return "Product{" +
	               "id=" + id +
	               ", name='" + nombre + '\'' +
	               ", price=" + descripcion +
	               '}';
	    } 

}
