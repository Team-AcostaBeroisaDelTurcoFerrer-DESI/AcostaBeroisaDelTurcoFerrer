package AcostaBeroisaDelTurcoFerrer.Controller.RecetaController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import AcostaBeroisaDelTurcoFerrer.DTO.RecetaDTO;
import AcostaBeroisaDelTurcoFerrer.DTO.RecetaListadoDTO;
import AcostaBeroisaDelTurcoFerrer.Entities.Receta;
import AcostaBeroisaDelTurcoFerrer.Service.RecetaService;
import AcostaBeroisaDelTurcoFerrer.repository.IngredienteRepository;

import java.util.List;


@Controller
@RequestMapping("/api/recetas")
public class RecetaController {

	@Autowired
	private RecetaService recetaService;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@PostMapping
	public String crearReceta(@RequestBody RecetaDTO recetaDTO, RedirectAttributes redirect) {
		try {
			recetaService.saveReceta(recetaDTO);
			return "redirect:/api/recetas/listado";
		}catch(IllegalArgumentException e) {
			return "redirect:/api/recetas/nueva";
			
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
	
	@GetMapping("/nueva")
	public String mostrarFormulario(Model model) {
	    model.addAttribute("receta", new RecetaDTO());
	    model.addAttribute("ingredientes", ingredienteRepository.findAll());
	    return "Receta/nuevaReceta";
	}

	
	@GetMapping("/listado")
	public String listarRecetas(Model model) {
	    List<Receta> recetas = recetaService.listarRecetas();
	    model.addAttribute("recetas", recetas);
	    return "Receta/listarReceta";
	}

}

