package AcostaBeroisaDelTurcoFerrer.DAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsistidoDAO extends JpaRepository<Asistido,Integer> {
	List<Asistido>findByNombreContaining(String nombre);	
	boolean existsByNombre(String nombre);
    Asistido findByDni(Long dni);
    Optional<Asistido> findById(Long id);	
}
