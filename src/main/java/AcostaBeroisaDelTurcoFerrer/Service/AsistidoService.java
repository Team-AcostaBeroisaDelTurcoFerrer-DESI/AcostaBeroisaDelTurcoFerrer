package AcostaBeroisaDelTurcoFerrer.Service;

import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import java.util.Optional; 

public interface AsistidoService {
    Asistido save(Asistido asistido);
    Optional<Asistido> findById(Long id); 
    Asistido findByDni(Long dni); 
}