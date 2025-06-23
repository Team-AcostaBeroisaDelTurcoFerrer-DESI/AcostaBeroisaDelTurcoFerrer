package AcostaBeroisaDelTurcoFerrer.Controller.AsistidoController;

import AcostaBeroisaDelTurcoFerrer.Entities.Familia;

import java.util.List;

public class FormBuscarController {
		
	private List<Familia> familia;
		public List<Familia> getfamilia() {
			return familia;
		}
		public void setfamilia(List<Familia> familia) {
			this.familia = familia;
		}
		
		
	private Long nroFamilia;
		public Long getnroFamilia() {
			return nroFamilia;
		}
		public void setdni(Long nroFamilia) {
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
	


