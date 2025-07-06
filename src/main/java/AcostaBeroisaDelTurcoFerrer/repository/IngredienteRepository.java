package AcostaBeroisaDelTurcoFerrer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import AcostaBeroisaDelTurcoFerrer.Entities.Ingredientes;

public interface IngredienteRepository extends JpaRepository<Ingredientes, Long> {
	
}
