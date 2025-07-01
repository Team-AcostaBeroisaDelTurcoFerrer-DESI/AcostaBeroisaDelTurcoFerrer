package AcostaBeroisaDelTurcoFerrer.Service;

import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.UncheckedException;

import java.util.List;


public interface FamiliaService {
	
    Familia getBynroFamilia(Long nroFamilia) throws CheckedException;	
	List<Familia>getAll();
	List<Familia> getByNombre(String Nombre);
	List<Familia> findAllActivas();					
	void logicalErase(Long nroFamilia);
	void save(Familia f) throws UncheckedException;  
	void update(Familia f) throws UncheckedException; 
	void deleteFamilia(Long nroFamilia); 
    void activarFamilia(Long nroFamilia);
}
