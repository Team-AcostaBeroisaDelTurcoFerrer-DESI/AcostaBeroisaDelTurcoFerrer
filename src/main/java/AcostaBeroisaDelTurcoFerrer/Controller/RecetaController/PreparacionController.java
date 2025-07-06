package AcostaBeroisaDelTurcoFerrer.Controller.RecetaController;

import AcostaBeroisaDelTurcoFerrer.DTO.PreparacionDTO;
import AcostaBeroisaDelTurcoFerrer.Entities.Preparacion;
//import AcostaBeroisaDelTurcoFerrer.Entities.Receta;
import AcostaBeroisaDelTurcoFerrer.repository.RecetaRepository;
import AcostaBeroisaDelTurcoFerrer.Service.PreparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import java.util.List;

@Controller
@RequestMapping("/preparaciones")
public class PreparacionController {

    @Autowired
    private PreparacionService preparacionService;

    @Autowired
    private RecetaRepository recetaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("preparaciones", preparacionService.listarTodas());
        return "preparaciones/listado";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("preparacion", new PreparacionDTO());
        model.addAttribute("recetas", recetaRepository.findAll());
        return "preparaciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute PreparacionDTO preparacion, RedirectAttributes redirect) {
        try {
            preparacionService.guardar(preparacion);
            redirect.addFlashAttribute("exito", "Preparación registrada correctamente.");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/preparaciones/nueva";
        }
        return "redirect:/preparaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        preparacionService.eliminar(id);
        return "redirect:/preparaciones";
    }
}
