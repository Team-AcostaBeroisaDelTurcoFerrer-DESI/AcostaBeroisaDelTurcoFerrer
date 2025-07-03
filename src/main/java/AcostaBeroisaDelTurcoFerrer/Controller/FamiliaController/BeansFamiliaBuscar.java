package AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController;

import java.util.List;

import AcostaBeroisaDelTurcoFerrer.Entities.Familia;

public class BeansFamiliaBuscar {
		
	public BeansFamiliaBuscar() {
		super();		
	}

	private List<Familia> familia;
	public List<Familia> getFamilia() {
		return familia;
	}
	public void setFamilia(List<Familia> familia) {
		this.familia = familia;
	}
	
	private Long nroFamilia;
	public Long getNroFamilia() {
		return nroFamilia;
	}
	public void setNroFamilia(Long nroFamilia) {		
		this.nroFamilia = nroFamilia;
	}	
	
	private String nombre;
	public String getNombre() {
		if(nombre!=null && nombre.length()>0)
			return nombre;
		else
			return null;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

}
