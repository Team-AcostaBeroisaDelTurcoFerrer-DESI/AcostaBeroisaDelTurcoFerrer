package AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;
import AcostaBeroisaDelTurcoFerrer.Service.FamiliaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/Familia/FamiliaBuscar") 
public class ControllerFamilySearch {

private static final String FORM_BEAN = "formBean";
@Autowired

private FamiliaService servicioFamilia;
   	
@GetMapping
public String preparaForm(Model modelo) {	
	
BeansFamilySearch form =  new BeansFamilySearch();
modelo.addAttribute(FORM_BEAN,form);
List<Familia> familiasActivas= servicioFamilia.findAllActivas();
modelo.addAttribute("familias", familiasActivas); // 
return "/Familia/FamiliaBuscar";
}
     
@PostMapping
public String handleFamiliaForm(@Valid @ModelAttribute(FORM_BEAN) BeansFamilySearch  formBean,BindingResult result,@RequestParam String action,Model model) throws CheckedException {

if (result.hasErrors()) {
model.addAttribute("familias", servicioFamilia.getAll()); 
return "/Familia/FamiliaBuscar";
}
switch (action) {
  case "actionBuscar": List<Familia> familiasEncontradas;	
    Familia familiaEncontrada;
    if (formBean.getNroFamilia() != null) {
    familiaEncontrada = servicioFamilia.getBynroFamilia((formBean.getNroFamilia())); 
    model.addAttribute("familias", familiaEncontrada);
    }else if (formBean.getNombre() != null && !formBean.getNombre().trim().isEmpty()) {
          familiasEncontradas = servicioFamilia.getByNombre(formBean.getNombre()); 
          model.addAttribute("Familias", familiasEncontradas);
          } else {
             familiasEncontradas = servicioFamilia.getAll();
            }
    model.addAttribute(FORM_BEAN, formBean);
    return "/Familia/FamiliaBuscar";
  case "actionRegistrar": return "redirect:/Familia/FamiliaCrear";
  case "actionCancelar": return "redirect:/";
  default: model.addAttribute("familias", servicioFamilia.getAll()); 
           return "/Familia/FamiliaBuscar";
                      
}
}
}
