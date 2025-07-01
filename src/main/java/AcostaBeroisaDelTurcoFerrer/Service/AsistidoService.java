package AcostaBeroisaDelTurcoFerrer.Service;

import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;

public interface AsistidoService {
    Asistido save(Asistido asistido); 
    Asistido update(Asistido asistido);
    Asistido findById(Long id); 
    Asistido findByDni(Long dni);  
    
}