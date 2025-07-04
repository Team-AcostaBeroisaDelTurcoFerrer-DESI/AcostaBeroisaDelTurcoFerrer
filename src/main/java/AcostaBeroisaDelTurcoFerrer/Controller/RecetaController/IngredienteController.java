package AcostaBeroisaDelTurcoFerrer.Controller.RecetaController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import AcostaBeroisaDelTurcoFerrer.DAO.IngredienteRepository;
import AcostaBeroisaDelTurcoFerrer.Entities.Ingredientes;


@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@PostMapping
	public Ingredientes crear(@RequestBody Ingredientes ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}
	
	@GetMapping
	public List<Ingredientes> listar(){
		return ingredienteRepository.findAll();
	}
}
