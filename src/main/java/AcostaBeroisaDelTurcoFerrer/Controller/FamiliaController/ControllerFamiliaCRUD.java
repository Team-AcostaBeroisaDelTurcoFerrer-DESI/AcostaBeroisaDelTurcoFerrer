package AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.UncheckedException;
import AcostaBeroisaDelTurcoFerrer.Service.FamiliaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/FamiliaCRUD") 
public class ControllerFamiliaCRUD {

@Autowired
private FamiliaService servicioFamilia;

@GetMapping({"", "/{nroFamilia}"}) 
public String preparaForm(Model modelo, @PathVariable("nroFamilia") Optional<Long> nroFamilia) {
Familia familia;
BeansFamilia formBean = null;
    if (nroFamilia.isPresent()) {
        try {
        formBean = (BeansFamilia) servicioFamilia.getBynroFamilia(nroFamilia.get());
        if (formBean.getAsistidos() == null) {
            formBean.setAsistidos(new ArrayList<>()); // Asegúrate de usar setAsistidos (plural)
        }
        if (formBean.getAsistidos().isEmpty()) {
            formBean.addAsistidos(new Asistido()); // Asegúrate de usar addAsistido (singular)
        }
        } catch (CheckedException e) { // Captura tu excepción personalizada
        familia = new Familia();
        familia.addAsistidos(new Asistido());
        modelo.addAttribute("errorMessage", "La familia con número " + nroFamilia.get() + " no fue encontrada. Se iniciará un nuevo registro.");
        }
    } else {
       formBean = new BeansFamilia();
       formBean.addAsistidos(new Asistido()); // Asegúrate de usar addAsistido (singular)
      }
      
    modelo.addAttribute("formBean", formBean);
    return "/FamiliaCRUD"; 
}

@ModelAttribute("allFamilia")
public List<Familia> getAllFamilia() {
    return this.servicioFamilia.getAll();
}

@GetMapping("/delete/{nroFamilia}")
public String deleteById(Model modelo, @PathVariable("nroFamilia") Long nroFamilia, RedirectAttributes redirectAttributes) {
    
    try {
        servicioFamilia.deleteBynroFamilia(nroFamilia);
        redirectAttributes.addFlashAttribute("successMessage", "Familia eliminada correctamente.");
    } catch (Exception e) { // Podrías usar una excepción más específica si no la encuentra
        redirectAttributes.addFlashAttribute("errorMessage", "No se pudo eliminar la familia con número " + nroFamilia + ".");
    }
    return "redirect:/FamiliaBuscar";
    }

@PostMapping
public String submit(@ModelAttribute("formBean") @Valid BeansFamilia formBean, 
                         BindingResult result,
                         ModelMap modelo,
                         @RequestParam(name = "action", required = false) String action) {
/*if (result.hasErrors()) {
    modelo.addAttribute("formBean", formBean);
    return "/FamiliaCRUD";
}*/

Familia familia = new Familia();
familia= formBean.toFamilia();      
              
if (action.equals("actionAceptar")) { 
try {
    servicioFamilia.save(familia);
    System.out.println("Familia creada");
    return "redirect:/"; 
} catch (UncheckedException e) {            
    result.addError(new ObjectError("asistidos[0].dni", e.getMessage()));
    modelo.addAttribute("formBean", formBean);
    return "/FamiliaCRUD";
} catch (IllegalArgumentException e) {
    result.addError(new ObjectError("globalError", e.getMessage()));
    modelo.addAttribute("formBean", formBean);
    return "redirect:/"; 
} catch (Exception e) {
    result.addError(new ObjectError("globalError", "Ocurrió un error inesperado al guardar la familia: " + e.getMessage()));
    modelo.addAttribute("formBean", formBean);
    return "redirect:/"; 
}

} else if (action.equals("actionCancelar")) {
    modelo.clear(); 
    return "redirect:/"; 
  }

return "redirect:/"; 
}

}