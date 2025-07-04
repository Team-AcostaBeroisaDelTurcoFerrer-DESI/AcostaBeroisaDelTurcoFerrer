package AcostaBeroisaDelTurcoFerrer.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import AcostaBeroisaDelTurcoFerrer.DAO.FamiliaDAO;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;


@Service
public class FamiliaServiceImp implements FamiliaService {

@Autowired
FamiliaDAO repo;
@Autowired
AsistidoService serviceAsistido;

@Override
@Transactional 
public void save(Familia f) throws CheckedException {

serviceAsistido.save(f.getAsistidos().get(0));

String n=f.getNombre();
f.setNombre(n+f.getAsistidos().get(0).getDni());	
f.setFechaRegistro(LocalDate.now());
repo.save(f);
}

@Override
public void update(Familia f) throws CheckedException {
	repo.save(f);
}

public Familia getBynroFamilia(Long nroFamilia) throws CheckedException {
    return repo.findById(nroFamilia)
            .orElseThrow(() -> new CheckedException("Familia con número " + nroFamilia + " no encontrada.", "nroFamilia"));
}


@Override
@Transactional(readOnly = true)
public List<Familia> findAllActivas() {	
    return repo.findByEstaActivaTrue(); 
}

@Override
@Transactional 
public void logicalErase(Long nroFamilia) throws CheckedException {
    Familia familia = repo.findById(nroFamilia)
                                       .orElseThrow(() -> new CheckedException("Familia con ID " + nroFamilia + " no encontrada para inactivar.", "nroFamilia"));
    familia.setEstaActiva(false); 
    repo.save(familia); 
}

@Override
@Transactional 
public void deleteFamilia(Long nroFamilia) {
    
    repo.deleteById(nroFamilia); 
}


@Override
@Transactional
public void activarFamilia(Long nroFamilia) throws CheckedException {
     Familia familia = repo.findById(nroFamilia)
                                       .orElseThrow(() -> new CheckedException("Familia con ID " + nroFamilia + " no encontrada para activar.", "nroFamilia"));
    familia.setEstaActiva(true);
     repo.save(familia);
 }


@Override
public List<Familia> buscarFamilias(Long nroFamilia, String nombre) {
	 List<Familia> resultados = new ArrayList<>();

     if (nroFamilia != null) {
         
         Optional<Familia> familiaOptional = repo.findByNroFamilia(nroFamilia);
         familiaOptional.ifPresent(resultados::add); 
     } else if (nombre != null && !nombre.trim().isEmpty()) {
        
         resultados = repo.findByNombreContainingIgnoreCase(nombre);
     } else {
        
         resultados = repo.findAll();
     }
     return resultados;
}
		
}
