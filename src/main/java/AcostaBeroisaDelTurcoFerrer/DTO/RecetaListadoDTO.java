package AcostaBeroisaDelTurcoFerrer.DTO;

public class RecetaListadoDTO {
	private String nombre;
	private int caloriasTotales;
	
	public RecetaListadoDTO(String nombre, int caloriasTotales) {
		this.nombre = nombre;
		this.caloriasTotales = caloriasTotales;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getCaloriasTotales() {
		return caloriasTotales;
	}
}
