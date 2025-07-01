package AcostaBeroisaDelTurcoFerrer.Service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
//import AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController.BeansFamilySearch;
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

@Override
@Transactional 
public void save(Familia f) throws UncheckedException {

if (f.getAsistidos() == null || f.getAsistidos().isEmpty()) {
 throw new UncheckedException ("Una familia debe tener al menos un integrante.");
}
if (f.getNroFamilia() == null) { 
 f.setFechaRegistro(LocalDate.now());
}

for (Asistido asistido : f.getAsistidos()) {	  
 if (asistido.getFamilia() == null) {
     asistido.setFamilia(f);
} 
if (asistido.getDni() != null) { 
  Asistido existingAsistido = serviceAsistido.findByDni(asistido.getDni());          
  if (existingAsistido != null && (asistido.getId() == null || !existingAsistido.getId().equals(asistido.getId()))) {
     throw new UncheckedException("El DNI " + asistido.getDni() + " ya está registrado para otro integrante.");
 }
}else{            
  throw new IllegalArgumentException("El DNI del asistido no puede ser nulo.");
 }
}
String nombreFamilia = f.getNombre()+f.getAsistidos().get(0).getDni();
f.setNombre(nombreFamilia);
repo.save(f); 
}

@Override
public void update(Familia f) throws UncheckedException {
	repo.save(f);
}

public Familia getBynroFamilia(Long nroFamilia) throws CheckedException {
    return repo.findById(nroFamilia)
            .orElseThrow(() -> new CheckedException("Familia con número " + nroFamilia + " no encontrada.", "nroFamilia"));
}

@Override
public List<Familia> getByNombre(String Nombre) {
	return repo.findByNombre(Nombre);	               
}

@Override
@Transactional(readOnly = true)
public List<Familia> findAllActivas() {
    // Necesitaremos un método en el repositorio para esto
    return repo.findByEstaActivaTrue(); 
}

@Override
@Transactional // Esta operación debe ser transaccional
public void logicalErase(Long nroFamilia) {
    Familia familia = repo.findById(nroFamilia)
                                       .orElseThrow(() -> new UncheckedException("Familia con ID " + nroFamilia + " no encontrada para inactivar.", "nroFamilia"));
    familia.setEstaActiva(false); // Marcar como inactiva
    // Si necesitas que los asistidos de esa familia también se marquen como inactivos,
    // deberías iterar sobre familia.getAsistidos() y setear asistido.setEstaActiva(false);
    // y luego guardar la familia.
    repo.save(familia); // Guardar el cambio de estado
}

@Override
@Transactional // Esta operación debe ser transaccional
public void deleteFamilia(Long nroFamilia) {
    // Opcional: Puedes verificar si la familia existe antes de intentar borrar
    // Familia familia = familiaRepository.findById(nroFamilia)
    //                                   .orElseThrow(() -> new AppUncheckedException("Familia con ID " + nroFamilia + " no encontrada para eliminar.", "nroFamilia"));
    
    // La eliminación física es directa
    repo.deleteById(nroFamilia); 
}


@Override
@Transactional
public void activarFamilia(Long nroFamilia) {//Reactivacion de una familia
     Familia familia = repo.findById(nroFamilia)
                                       .orElseThrow(() -> new UncheckedException("Familia con ID " + nroFamilia + " no encontrada para activar.", "nroFamilia"));
    familia.setEstaActiva(true);
     repo.save(familia);
 }
		
}
