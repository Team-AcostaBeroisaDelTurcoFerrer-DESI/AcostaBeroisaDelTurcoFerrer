package AcostaBeroisaDelTurcoFerrer.Service;

import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;

public interface AsistidoService {
    Asistido save(Asistido asistido) throws CheckedException; 
    Asistido update(Asistido asistido);
    Asistido findById(Long id);      
    void logicalErase(Long nroFamilia) throws CheckedException;
}