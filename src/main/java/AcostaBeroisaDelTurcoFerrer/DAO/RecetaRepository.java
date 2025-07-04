package AcostaBeroisaDelTurcoFerrer.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import AcostaBeroisaDelTurcoFerrer.Entities.Receta;

import java.util.Optional;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
	Optional<Receta> findByNombre(String nombre);
	Optional<Receta> findByIdAndIsActiveTrue(Long id);

}
