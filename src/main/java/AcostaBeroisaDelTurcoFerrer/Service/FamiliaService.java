package AcostaBeroisaDelTurcoFerrer.Service;

import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController.*;
import java.util.List;

public interface FamiliaService {
	
	Familia getBynroFamilia(Long nroFamilia);	
	List<Familia>getAll();

	List<Familia> getByNombre(String Nombre);	
	List<Familia>filter(BeansFamiliaBuscar filter) throws Exception;	//???	
	
	void deleteBynroFamilia(Long nroFamilia) throws Exception;	
	void save(Familia f) throws Exception;
	
	
	

}
