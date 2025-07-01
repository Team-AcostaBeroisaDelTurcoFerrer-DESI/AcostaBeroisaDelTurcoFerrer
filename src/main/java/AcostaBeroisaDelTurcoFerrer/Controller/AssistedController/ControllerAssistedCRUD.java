package AcostaBeroisaDelTurcoFerrer.Controller.AssistedController;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.UncheckedException;
import AcostaBeroisaDelTurcoFerrer.Service.AsistidoService;
import AcostaBeroisaDelTurcoFerrer.Service.FamiliaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/Asistidos")
public class ControllerAssistedCRUD {
private static final String ID2 = "id";
private static final String FORM_BEAN = "formBean";
private static final String NRO_FAMILIA = "nroFamilia";
@Autowired
private AsistidoService servicioAsistidos;
@Autowired
private FamiliaService servicioFamilia;

private BeansAssisted formBean;

@GetMapping("/AsistidosCrear/{nroFamilia}")
public String preparaForm(@PathVariable(NRO_FAMILIA) Long nroFamilia,Model modelo) throws CheckedException {	
formBean = new BeansAssisted(); 
formBean.setFamilia(servicioFamilia.getBynroFamilia(nroFamilia));
modelo.addAttribute(FORM_BEAN, formBean);
return "/Asistidos/AsistidosCrear"; 
}
@GetMapping("/AsistidosEditar/{id}")
public String preparaFormEditar(@PathVariable(ID2) Long id,Model modelo) throws CheckedException {	
Asistido a = servicioAsistidos.findById(id);
formBean = new BeansAssisted();
formBean.setId(a.getId());
formBean.setApellido(a.getApellido());
formBean.setNombre(a.getNombre());
formBean.setDni(a.getDni());
formBean.setDireccion(a.getDireccion());
formBean.setFechaNacimiento(a.getFechaNacimiento());
formBean.setFechaRegistro(a.getFechaRegistro());
formBean.setOcupacion(a.getOcupacion());
formBean.setFamilia(a.getFamilia());

modelo.addAttribute(FORM_BEAN, formBean);
return "/Asistidos/AsistidosEditar"; 
}

@PostMapping("/AsistidosCrear") 
public String guardarFamilia(@Valid @ModelAttribute(FORM_BEAN) BeansAssisted formBean,
                            BindingResult bindingResult,
                            Model modelo,
                            RedirectAttributes redirectAttributes,
                            @RequestParam String action) throws UncheckedException, CheckedException {
	
if ("actionCancelar".equals(action)) {
        redirectAttributes.addFlashAttribute("infoMessage", "Operación cancelada.");
        return "redirect:/";	
        
        
}

else if("actionAceptar".equals(action)) {    	
    	/*if (bindingResult.hasErrors()) {      
        modelo.addAttribute("formBean", formBean);
        modelo.addAttribute("error", "Por favor, corrige los errores en el formulario.");
        return "Familia/FamiliaCrear";
        }else { */  
    	try {
    	Familia familia = servicioFamilia.getBynroFamilia(formBean.getFamilia().getNroFamilia());	
        Asistido asistido = new Asistido();
        asistido.setApellido(formBean.getApellido());
        asistido.setNombre(formBean.getNombre());
        asistido.setDireccion(formBean.getDireccion());
        asistido.setDni(formBean.getDni());      
        asistido.setFamilia(familia); 
        asistido.setFechaNacimiento(formBean.getFechaNacimiento());
        asistido.setFechaRegistro(formBean.getFechaRegistro());        
        asistido.setOcupacion(formBean.getOcupacion());
       
        if (asistido.getFechaRegistro() == null) {
            asistido.setFechaRegistro(LocalDate.now());
        }
        
        servicioAsistidos.save(asistido);
        
		} catch (UncheckedException e) {			
			e.printStackTrace();
		}
        redirectAttributes.addFlashAttribute("exitoMessage", "Familia guardada/actualizada exitosamente!");
        return "redirect:/";
        }          
    return "redirect:/";
}       
@PostMapping("/AsistidosEditar") 
public String ActualizarFamilia(@Valid @ModelAttribute(FORM_BEAN) BeansAssisted formBean,
                            BindingResult bindingResult,
                            Model modelo,
                            RedirectAttributes redirectAttributes,
                            @RequestParam String action) throws CheckedException {
	
if ("actionCancelar".equals(action)) {
        redirectAttributes.addFlashAttribute("infoMessage", "Operación cancelada.");
        return "redirect:/";	
        
        
}

else if("actionAceptar".equals(action)) {    	
    	/*if (bindingResult.hasErrors()) {      
        modelo.addAttribute("formBean", formBean);
        modelo.addAttribute("error", "Por favor, corrige los errores en el formulario.");
        return "Familia/FamiliaCrear";
        }else { */  
    	try {   		
        Asistido asistido = servicioAsistidos.findById(formBean.getId());
        
        asistido.setApellido(formBean.getApellido());
        asistido.setNombre(formBean.getNombre());
        asistido.setDireccion(formBean.getDireccion());        
        asistido.setFechaNacimiento(formBean.getFechaNacimiento());        
        asistido.setOcupacion(formBean.getOcupacion());
               
        servicioAsistidos.update(asistido);
        
		} catch (UncheckedException e) {			
			e.printStackTrace();
		}
        redirectAttributes.addFlashAttribute("exitoMessage", "Familia guardada/actualizada exitosamente!");
        return "redirect:/";
        }          
    return "redirect:/";
}         

	
	

}
