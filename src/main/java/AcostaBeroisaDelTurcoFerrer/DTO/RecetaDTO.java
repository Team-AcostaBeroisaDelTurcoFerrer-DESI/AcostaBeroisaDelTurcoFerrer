package AcostaBeroisaDelTurcoFerrer.DTO;
import java.util.List;
public class RecetaDTO {
	private String nombre;
	private String descripcion;
	private List<IngredienteRecetaDTO> ingredientes;
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
	public List<IngredienteRecetaDTO> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(List<IngredienteRecetaDTO> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	
}
