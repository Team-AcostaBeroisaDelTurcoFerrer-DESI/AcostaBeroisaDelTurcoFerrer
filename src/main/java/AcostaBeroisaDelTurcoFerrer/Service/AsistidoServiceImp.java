package AcostaBeroisaDelTurcoFerrer.Service;
import AcostaBeroisaDelTurcoFerrer.DAO.AsistidoDAO; 
import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AsistidoServiceImp implements AsistidoService {

    @Autowired
    private AsistidoDAO repoAsistido; 
    @Override
	public Asistido update(Asistido asistido) {   	         
       return repoAsistido.save(asistido);
	}
@Override
@Transactional
public Asistido save(Asistido asistido) throws CheckedException {
    	
      if (asistido.getId() == null && asistido.getFechaRegistro() == null) {
            asistido.setFechaRegistro(LocalDate.now());
        }
 
        if (asistido.getDni() != null) {
            Asistido existingAsistido = repoAsistido.findByDni(asistido.getDni());
            if (existingAsistido != null && (asistido.getId() == null || !existingAsistido.getId().equals(asistido.getId()))) {
                throw new CheckedException("El DNI " + asistido.getDni() + " ya está registrado.", "dni");
            }
        } else {
            throw new CheckedException("El DNI del asistido no puede ser nulo.", "dni");
        }

       return repoAsistido.save(asistido);
    }

@Override
@Transactional(readOnly = true)
public Asistido findById(Long id) {
        return repoAsistido.findById(id);
}

@Override
 public void logicalErase(Long id) throws CheckedException {
    Asistido asistido = repoAsistido.findById(id);
    if (asistido == null) {
        throw new CheckedException("Asistido no encontrado con ID: " + id, "id");
    }
    asistido.setEstaActiva(false);
    
    repoAsistido.save(asistido);
}

	
 
}