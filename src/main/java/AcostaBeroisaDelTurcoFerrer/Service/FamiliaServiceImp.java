package AcostaBeroisaDelTurcoFerrer.Service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
//import AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController.BeansFamiliaBuscar;
import AcostaBeroisaDelTurcoFerrer.DAO.FamiliaDAO;
import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.UncheckedException;

@Service
public class FamiliaServiceImp implements FamiliaService {

@Autowired
FamiliaDAO repo;
@Autowired
AsistidoService serviceAsistido;

@Override
public List<Familia> getAll() {
	return repo.findAll();
}

/*@Override
public List<Familia> filter(BeansFamiliaBuscar filter) throws Exception 
{
 if(filter.getNombre()==null && filter.getNroFamilia()==null)
 throw new Exception("Es necesario al menos un filtro");
 else
 return repo.findByNombreOrNroFamilia(filter.getNombre(),(long) filter.getNroFamilia());		
}*/	

@Override
public void deleteBynroFamilia(Long nroFamilia) {
repo.deleteById(nroFamilia);	
}

@Override
@Transactional 
public void save(Familia familia) throws Exception {

if (familia.getAsistidos() == null || familia.getAsistidos().isEmpty()) {
 throw new UncheckedException ("Una familia debe tener al menos un integrante.");
}
if (familia.getNroFamilia() == null) { 
 familia.setFechaRegistro(LocalDate.now());
}
  for (Asistido asistido : familia.getAsistidos()) {
    if (asistido.getFamilia() == null) {
        asistido.setFamilia(familia);
    }
    if (asistido.getDni() != null) { 
        Asistido existingAsistido = serviceAsistido.findByDni(asistido.getDni());          
        if (existingAsistido != null && (asistido.getId() == null || !existingAsistido.getId().equals(asistido.getId()))) {
            throw new CheckedException("El DNI " + asistido.getDni() + " ya está registrado para otro integrante.");
        }
    } else {            
        throw new IllegalArgumentException("El DNI del asistido no puede ser nulo.");
      }
  }
repo.save(familia); // Guarda la familia y sus asistidos en cascada debido a CascadeType.ALL
}
	
@Override
public Familia getBynroFamilia(Long nroFamilia) throws CheckedException { // Declara que lanza una checked exception
    return repo.findById(nroFamilia)
            .orElseThrow(() -> new CheckedException("Familia con número " + nroFamilia + " no encontrada.", "nroFamilia"));
}

@Override
public List<Familia> getByNombre(String Nombre) {
	return repo.findByNombre(Nombre);	               
}
		
}
