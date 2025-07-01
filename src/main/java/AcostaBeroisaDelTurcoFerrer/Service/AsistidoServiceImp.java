package AcostaBeroisaDelTurcoFerrer.Service;
import AcostaBeroisaDelTurcoFerrer.DAO.AsistidoDAO; 
import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.UncheckedException;
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
    public Asistido save(Asistido asistido) {
    	
      if (asistido.getId() == null && asistido.getFechaRegistro() == null) {
            asistido.setFechaRegistro(LocalDate.now());
        }
 
        if (asistido.getDni() != null) {
            Asistido existingAsistido = repoAsistido.findByDni(asistido.getDni());
            if (existingAsistido != null && (asistido.getId() == null || !existingAsistido.getId().equals(asistido.getId()))) {
                throw new UncheckedException("El DNI " + asistido.getDni() + " ya está registrado.", "dni");
            }
        } else {
            throw new UncheckedException("El DNI del asistido no puede ser nulo.", "dni");
        }

        return repoAsistido.save(asistido);
    }

    @Override
    @Transactional(readOnly = true)
    public Asistido findById(Long id) {
        return repoAsistido.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Asistido findByDni(Long dni) {
        return repoAsistido.findByDni(dni);
    }

	
 
}