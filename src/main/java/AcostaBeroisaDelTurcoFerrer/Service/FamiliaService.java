package AcostaBeroisaDelTurcoFerrer.Service;

import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;
//import AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController.*;
import java.util.List;


public interface FamiliaService {
	
	Object getBynroFamilia(Long nroFamilia) throws CheckedException;	
	List<Familia>getAll();
	List<Familia> getByNombre(String Nombre);	
	//List<Familia>filter(BeansFamiliaBuscar filter) throws Exception;	//???		
	void deleteBynroFamilia(Long nroFamilia) throws Exception;	
	void save(Familia f) throws Exception;  		
}
