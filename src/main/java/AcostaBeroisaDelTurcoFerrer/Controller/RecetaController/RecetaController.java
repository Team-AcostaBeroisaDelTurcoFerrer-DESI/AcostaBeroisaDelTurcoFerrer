package AcostaBeroisaDelTurcoFerrer.Controller.RecetaController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import AcostaBeroisaDelTurcoFerrer.DTO.RecetaDTO;
import AcostaBeroisaDelTurcoFerrer.DTO.RecetaListadoDTO;

import AcostaBeroisaDelTurcoFerrer.Service.RecetaService;

import java.util.List;


@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

	@Autowired
	private RecetaService recetaService;
	
	@PostMapping
	public String crearReceta(@RequestBody RecetaDTO recetaDTO) {
		try {
			recetaService.saveReceta(recetaDTO);
			return "Receta guardada correctamente.";	
		}catch(IllegalArgumentException e) {
			return e.getMessage();
		}
	}
	
	@GetMapping
	public List<RecetaListadoDTO> listarRecetas(
	    @RequestParam(required = false) String nombre,
	    @RequestParam(required = false) Integer minCalorias,
	    @RequestParam(required = false) Integer maxCalorias
	) {

	    return recetaService.getReceta(nombre, minCalorias, maxCalorias);
	}
	
	@DeleteMapping("/{id}")
	public String eliminarReceta(@PathVariable Long id) {
	    recetaService.deleteReceta(id);
		return "Receta eliminada.";
	}

	@PutMapping("/{id}")
	public String modificarReceta(@PathVariable Long id, @RequestBody RecetaDTO recetaDTO) {
		recetaService.updateReceta(recetaDTO, id);
		return "Receta modificada con éxito.";
	}
}

