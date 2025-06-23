package AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.Service.FamiliaService;




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/FamiliaBuscar") 
public class ControllerFamiliaSearch {
	@Autowired
    private FamiliaService servicioFamilia;
   
	
@GetMapping
public String preparaForm(Model modelo) {
    	
       BeansFamiliaBuscar form =  new BeansFamiliaBuscar();
       modelo.addAttribute("formBean",form);

       List<Familia> todasLasFamilias = servicioFamilia.getAll();
       modelo.addAttribute("familias", todasLasFamilias); // 

       return "/FamiliaBuscar";
       
    }

     
@PostMapping
public String handleFamiliaForm(@Validated @ModelAttribute("formBean") BeansFamiliaBuscar  formBean,
            BindingResult result,
            @RequestParam String action,
            Model model) {

if (result.hasErrors()) {
// Si hay errores, volvemos a cargar todas las familias para que la vista las muestre
model.addAttribute("familias", servicioFamilia.getAll()); // ¡LLAMADA AL SERVICIO!
return "/FamiliaBuscar";
}

switch (action) {
case "actionBuscar":
	
List<Familia> familiasEncontradas;	
Familia familiaEncontrada;

if (formBean.getNroFamilia() != null) {
familiaEncontrada = servicioFamilia.getBynroFamilia(formBean.getNroFamilia()); // ¡LLAMADA AL SERVICIO!
model.addAttribute("familias", familiaEncontrada);
} 
else if (formBean.getNombre() != null && !formBean.getNombre().trim().isEmpty()) {
familiasEncontradas = servicioFamilia.getByNombre(formBean.getNombre()); // ¡LLAMADA AL SERVICIO!
model.addAttribute("Familias", familiasEncontradas);
} else {
familiasEncontradas = servicioFamilia.getAll(); // ¡LLAMADA AL SERVICIO!
}

model.addAttribute("formBean", formBean);
return "/FamiliaBuscar";

case "actionRegistrar":
return "redirect:/FamiliaCrear";

case "actionCancelar":
return "redirect:/";

default:
model.addAttribute("familias", servicioFamilia.getAll()); // ¡LLAMADA AL SERVICIO!
return "/FamiliaBuscar";
}

}

}
