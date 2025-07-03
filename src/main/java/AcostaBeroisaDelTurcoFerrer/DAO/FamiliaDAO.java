package AcostaBeroisaDelTurcoFerrer.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import java.util.List;
import java.util.Optional;

@Repository
public interface FamiliaDAO extends JpaRepository<Familia,Long> {
				
	List<Familia> findByEstaActivaTrue();
	
    Optional<Familia> findByNroFamilia(Long nroFamilia);

    // Método para buscar por nombre (ignorando mayúsculas/minúsculas y parcial)
    List<Familia> findByNombreContainingIgnoreCase(String nombre);
	List<Familia>findByNombre(String nombre);	
	
}
