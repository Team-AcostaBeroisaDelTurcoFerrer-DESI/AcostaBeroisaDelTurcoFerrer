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
    
 // Relación OneToMany: Una Receta puede tener muchas Preparaciones
    // 'mappedBy' indica que el lado "muchos" (Preparacion) es el propietario de la relación
    // CascadeType.ALL: Las operaciones de persistencia (guardar, actualizar, eliminar)
    // se propagarán desde Receta a Preparacion.
    // orphanRemoval = true: Si una Preparacion se elimina de la lista de 'preparaciones'
    // de una Receta, también se eliminará de la base de datos.
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Preparacion> preparaciones = new ArrayList<>(); // Inicializar para evitar NullPointerException

 // Relación OneToMany con la entidad de unión ItemsReceta
    // mappedBy indica que el lado propietario de la relación es 'receta' en ItemsReceta
    // CascadeType.ALL para propagar operaciones de persistencia
    // orphanRemoval = true: si un ItemsReceta se desvincula de la lista, se elimina
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemsReceta> itemsRecetas = new HashSet<>(); // Usar Set para evitar duplicados

    
    
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
	
	// Métodos de conveniencia para añadir y quitar preparaciones
    // Esto asegura que ambos lados de la relación estén sincronizados
    public void addPreparacion(Preparacion preparacion) {
        preparaciones.add(preparacion);
        preparacion.setReceta(this);
    }

    public void removePreparacion(Preparacion preparacion) {
        preparaciones.remove(preparacion);
        preparacion.setReceta(null);
    }

 // Métodos de conveniencia para añadir y quitar ItemsReceta
    // Aseguran que ambos lados de la relación estén sincronizados
    public void addItemReceta(Ingredientes ingrediente, int cantidad) {
        ItemsReceta itemReceta = new ItemsReceta(this, ingrediente, cantidad);
        this.itemsRecetas.add(itemReceta);
        ingrediente.getItemsRecetas().add(itemReceta); // Sincroniza el lado de Ingrediente
    }

    public void removeItemReceta(ItemsReceta itemReceta) {
        this.itemsRecetas.remove(itemReceta);
        itemReceta.getIngrediente().getItemsRecetas().remove(itemReceta); // Sincroniza
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
