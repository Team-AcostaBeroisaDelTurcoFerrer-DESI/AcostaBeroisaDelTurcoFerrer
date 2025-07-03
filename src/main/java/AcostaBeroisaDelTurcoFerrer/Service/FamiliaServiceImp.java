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
/*	
Asistido existente = serviceAsistido.findByDni(f.getAsistidos().get(0).getDni());	

if (f.getAsistidos() == null || f.getAsistidos().isEmpty()) {
        throw new CheckedException("Una familia debe tener al menos un integrante.", "Familia sin integrantes");
}
  

if (existente != null && (f.getAsistidos().get(0).getId() == null || !f.getAsistidos().get(0).equals(existente.getId()))) {            
   throw new CheckedException("Ya existe un asistido registrado con el DNI: " + f.getAsistidos().get(0).getDni());
   } */
	
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
@Transactional // Esta operación debe ser transaccional
public void logicalErase(Long nroFamilia) throws CheckedException {
    Familia familia = repo.findById(nroFamilia)
                                       .orElseThrow(() -> new CheckedException("Familia con ID " + nroFamilia + " no encontrada para inactivar.", "nroFamilia"));
    familia.setEstaActiva(false); 
    repo.save(familia); 
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
public void activarFamilia(Long nroFamilia) throws CheckedException {//Reactivacion de una familia
     Familia familia = repo.findById(nroFamilia)
                                       .orElseThrow(() -> new CheckedException("Familia con ID " + nroFamilia + " no encontrada para activar.", "nroFamilia"));
    familia.setEstaActiva(true);
     repo.save(familia);
 }


@Override
public List<Familia> buscarFamilias(Long nroFamilia, String nombre) {
	 List<Familia> resultados = new ArrayList<>();

     if (nroFamilia != null) {
         // Si busca por número de familia
         Optional<Familia> familiaOptional = repo.findByNroFamilia(nroFamilia);
         familiaOptional.ifPresent(resultados::add); // Añade si existe
     } else if (nombre != null && !nombre.trim().isEmpty()) {
         // Si busca por nombre
         resultados = repo.findByNombreContainingIgnoreCase(nombre);
     } else {
         // Si no se proporciona ningún criterio específico, podrías devolver todo
         // o una lista vacía, dependiendo de tu lógica de negocio
         resultados = repo.findAll();
     }
     return resultados;
}
		
}
