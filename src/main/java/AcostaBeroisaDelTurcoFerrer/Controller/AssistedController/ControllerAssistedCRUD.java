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
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;
import AcostaBeroisaDelTurcoFerrer.Service.AsistidoService;
import AcostaBeroisaDelTurcoFerrer.Service.FamiliaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/Asistidos")
public class ControllerAssistedCRUD {

private static final String ID2 = "id";
private static final String FORM_BEAN = "formBean";
private static final String NRO_FAMILIA = "nroFamilia";
private static final String ERROR_MESSAGE = "errorMessage";
private static final String EXITO_MESSAGE = "exitoMessage";

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
@GetMapping("/AsistidosBorrar/{id}")
public String prepareFormBorrar(@PathVariable(ID2) Long id, Model modelo,
                                    RedirectAttributes redirectAttributes) throws CheckedException {
    
    try {
        Asistido asistido = servicioAsistidos.findById(id);
        modelo.addAttribute("asistido", asistido);
        return "/Asistidos/AsistidosBorrar";
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Error inesperado al preparar el borrado del asistido: " + e.getMessage());
        e.printStackTrace();
    return "redirect:/Asistidos/AsistidosBuscar";
    }
}

@PostMapping("/inactivar/{id}")
public String inactivarAsistido(@PathVariable(ID2) Long id, Model modelo,
                                    RedirectAttributes redirectAttributes) throws CheckedException {
	Long nroFamilia = null;
    try {
        Asistido asistido = servicioAsistidos.findById(id);
        if (asistido != null && asistido.getFamilia() != null) {
            nroFamilia = asistido.getFamilia().getNroFamilia();
        } else {          
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Error: Asistido o familia no encontrados para inactivar.");
            return "redirect:/"; 
        }
        servicioAsistidos.logicalErase(id);
        redirectAttributes.addFlashAttribute(EXITO_MESSAGE, "Asistido inactivado exitosamente!");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Error al inactivar el asistido: " + e.getMessage());
        e.printStackTrace(); 
    }

    if (nroFamilia == null) {
        return "redirect:/";
    }  
    return "redirect:/Familia/verIntegrantes/" + nroFamilia;
}



@PostMapping("/AsistidosCrear") 
public String guardarFamilia(@Valid @ModelAttribute(FORM_BEAN) BeansAssisted formBean,
                            BindingResult bindingResult,
                            Model modelo,
                            RedirectAttributes redirectAttributes,
                            @RequestParam String action) throws CheckedException{
	
Long nroFamilia = formBean.getFamilia() != null ? formBean.getFamilia().getNroFamilia() : null;

if ("actionAceptar".equals(action)) {
    	
    try {
    	Asistido asistido = new Asistido();
    	asistido.setApellido(formBean.getApellido());
    	asistido.setNombre(formBean.getNombre());
    	asistido.setDireccion(formBean.getDireccion());
    	asistido.setDni(formBean.getDni());
    	asistido.setFechaNacimiento(formBean.getFechaNacimiento());
    	asistido.setOcupacion(formBean.getOcupacion());
    	asistido.setFamilia(servicioFamilia.getBynroFamilia(nroFamilia));
    	
    	if(asistido.getFechaRegistro()==null) {
    		asistido.setFechaRegistro(LocalDate.now());
    	}
    	 	  	
    	servicioAsistidos.save(asistido);
        redirectAttributes.addFlashAttribute("exitoMessage", "Familia Registrada");
        return "redirect:/"; 
    } catch (CheckedException e) {        
        e.printStackTrace();           
        if (e.getMessage() != null && e.getMessage().contains("Ya existe un asistido registrado con el DNI")) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: Ya existe un asistido registrado con ese DNI, que pertenece a una familia.");
            return "redirect:/Asistidos/AsistidosCrear/" + nroFamilia; 
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Ocurrió un error inesperado al guardar la familia.");
            return "redirect:/Asistidos/AsistidosCrear/" + nroFamilia;  
        }
    } catch (Exception e) {
        e.printStackTrace(); 
        redirectAttributes.addFlashAttribute("errorMessage", "Ocurrió un error inesperado. Por favor, intente de nuevo más tarde.");
        return "redirect:/Asistidos/AsistidosCrear/" + nroFamilia;
    }	
}else if ("actionCancelar".equals(action)) {
  redirectAttributes.addFlashAttribute("infoMessage", "Operación cancelada.");
return "redirect:/";        
}          
return "redirect:/error";
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
    	Asistido asistido = servicioAsistidos.findById(formBean.getId());
        
        asistido.setApellido(formBean.getApellido());
        asistido.setNombre(formBean.getNombre());
        asistido.setDireccion(formBean.getDireccion());        
        asistido.setFechaNacimiento(formBean.getFechaNacimiento());        
        asistido.setOcupacion(formBean.getOcupacion());
               
        servicioAsistidos.update(asistido);
        redirectAttributes.addFlashAttribute("exitoMessage", "Familia guardada/actualizada exitosamente!");
        return "redirect:/";
        }          
    return "redirect:/";
}         

}
