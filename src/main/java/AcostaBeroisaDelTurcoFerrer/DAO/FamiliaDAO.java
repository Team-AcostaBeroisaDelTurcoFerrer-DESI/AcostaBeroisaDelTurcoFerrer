package AcostaBeroisaDelTurcoFerrer.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import java.util.List;

@Repository
public interface FamiliaDAO extends JpaRepository<Familia,Long> {
	
	List<Familia>findByNombre(String nombre);	
	boolean existsByNombre(String nombre);	
	boolean findByNombreAndNroFamilia(String nombre, Long nroFamilia);		
	List<Familia> findByNombreOrNroFamilia(String nombre,Long nroFamilia);	
	List<Familia> findByEstaActivaTrue();
}
