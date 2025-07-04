package AcostaBeroisaDelTurcoFerrer.Service;

import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;
import java.util.List;

public interface FamiliaService {
	
    Familia getBynroFamilia(Long nroFamilia) throws CheckedException;	
	
	List<Familia> buscarFamilias(Long nroFamilia, String nombre);
	List<Familia> findAllActivas();		
	
	void logicalErase(Long nroFamilia) throws CheckedException;	
	void save(Familia f) throws CheckedException;	
	void update(Familia f) throws CheckedException; 	
	void deleteFamilia(Long nroFamilia); 	
    void activarFamilia(Long nroFamilia) throws CheckedException;
    
    
    

   
    
   
    
    
    
}
