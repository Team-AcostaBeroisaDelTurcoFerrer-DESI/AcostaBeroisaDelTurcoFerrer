package AcostaBeroisaDelTurcoFerrer.Service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;



import AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController.BeansFamiliaBuscar;
import AcostaBeroisaDelTurcoFerrer.DAO.FamiliaDAO;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;

@Service
public class FamiliaServiceImp implements FamiliaService {

	@Autowired
	FamiliaDAO repo;
	
	
	@Override
	public List<Familia> getAll() {
		return repo.findAll();
	}

	@Override
	public List<Familia> filter(BeansFamiliaBuscar filter) throws Exception 
	{
		//ver https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
		if(filter.getNombre()==null && filter.getNroFamilia()==null)
			throw new Exception("Es necesario al menos un filtro");
		else
			return repo.findByNombreOrNroFamilia(filter.getNombre(),(long) filter.getNroFamilia());
				
	}
	

	@Override
	public void deleteBynroFamilia(Long nroFamilia) {
		repo.deleteById(nroFamilia);	
	}

	@Override
	@Transactional // Asegura que toda la operación de guardado sea una transacción atómica


	public void save(Familia familia) {	
		
        // Establecer la fecha de registro solo para nuevas familias
        if (familia.getNroFamilia() == null) {
            familia.setFechaRegistro(LocalDate.now());
        }

        // Asegúrate de que cada asistido esté vinculado a esta familia
        // (Esto es importante para la relación bidireccional si no se usó addAsistido)
        if (familia.getAsistidos() != null) {
            for (Asistido asistido : familia.getAsistidos()) {
                if (asistido.getFamilia() == null) { // Solo si aún no está vinculado
                    asistido.setFamilia(familia);
                }
            }
        }

        // Si la lógica de negocio dice que debe haber al menos un asistido,
        // el controlador ya debería haberlo validado antes de llamar a este servicio,
        // o puedes añadir una validación aquí también.
        if (familia.getAsistidos() == null || familia.getAsistidos().isEmpty()) {
            throw new IllegalArgumentException("Una familia debe tener al menos un integrante.");
        }

        return familiaRepository.save(familia); // Guarda la familia y sus asistidos en cascada
    }		
	

	@Override
	public Familia getBynroFamilia(Long nroFamilia) {	
	    return repo.findById(nroFamilia)
	               .orElseThrow(() -> new NoSuchElementException("Familia con número " + nroFamilia + " no encontrada."));	    
	}

	@Override
	public List<Familia> getByNombre(String Nombre) {
		return repo.findByNombre(Nombre);	               
	}

	
	
}
