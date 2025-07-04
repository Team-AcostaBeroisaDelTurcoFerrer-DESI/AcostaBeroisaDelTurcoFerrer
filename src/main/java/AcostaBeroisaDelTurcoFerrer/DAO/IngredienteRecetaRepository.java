package AcostaBeroisaDelTurcoFerrer.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import AcostaBeroisaDelTurcoFerrer.Entities.ItemsReceta;

public interface IngredienteRecetaRepository extends JpaRepository<ItemsReceta, Long> {
	
}
