package AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;
import AcostaBeroisaDelTurcoFerrer.Service.FamiliaService;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/Familia") 
public class ControllerFamiliaCRUD {

private static final String ERROR_MESSAGE = "errorMessage";
private static final String EXITO_MESSAGE = "exitoMessage";
private static final String FORM_BEAN = "formBean";
private static final String NRO_FAMILIA = "nroFamilia";
@Autowired
private FamiliaService servicioFamilia;

@GetMapping("/FamiliaCrear")
public String preparaForm(Model modelo) {//PREPARA EL FORM PARA CREAR
BeansFamilia formBean = new BeansFamilia();	
formBean.addAsistidos(new Asistido());
     
modelo.addAttribute(FORM_BEAN, formBean);
return "/Familia/FamiliaCrear"; 
}
@GetMapping("/FamiliaEditar/{nroFamilia}")
public String prepareFormEditar (Model modelo,//PREPARA EL FORM PARA EDITAR
	   @PathVariable(NRO_FAMILIA) Long nroFamilia) throws CheckedException{
		
Familia f= servicioFamilia.getBynroFamilia(nroFamilia);
BeansFamilia formBean = new BeansFamilia();
	formBean.setNroFamilia(f.getNroFamilia());
    formBean.setNombre(f.getNombre());
    formBean.setFechaRegistro(f.getFechaRegistro()); 
    
	modelo.addAttribute(FORM_BEAN, formBean);
	return "/Familia/FamiliaEditar"; 
}
@GetMapping("/FamiliaBorrar/{nroFamilia}")
public String preparaBorradoFamilia(@PathVariable(NRO_FAMILIA) Long nroFamilia, Model modelo,//PREPARA EL FORM PARA BORRAR
                                    RedirectAttributes redirectAttributes) {
    try {
        Familia familia = servicioFamilia.getBynroFamilia(nroFamilia);                                    
        modelo.addAttribute("familia", familia); 
        return "/Familia/FamiliaBorrar"; 
    } catch (CheckedException e) {
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, e.getMessage());
        return "redirect:/FamiliaBuscar";
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Error inesperado al preparar el borrado de la familia: " + e.getMessage());
        e.printStackTrace();
        return "redirect:/FamiliaBuscar";
    }
}

/****************************************************************************************************** */

@PostMapping("/FamiliaEditar") 
public String editarFamilia(@Valid @ModelAttribute(FORM_BEAN) BeansFamilia formBean, //POST PARA EDITAR		                     
                             BindingResult bindingResult,
                             Model modelo,
                             RedirectAttributes redirectAttributes,
                             @RequestParam String action) throws CheckedException {
if ("actionCancelar".equals(action)) {
   redirectAttributes.addFlashAttribute("infoMessage", "Operación cancelada.");
   return "redirect:/Familia/FamiliaBuscar";
 }else if("actionAceptar".equals(action)) {	    	
	if (bindingResult.hasErrors()) {      
	   modelo.addAttribute("formBean", formBean);
	   modelo.addAttribute("error", "Por favor, corrige los errores en el formulario.");
	return "Familia/FamiliaBuscar";
    }else {  	
	   try {
	    		
	    		Familia f= servicioFamilia.getBynroFamilia(formBean.getNroFamilia());
	    		f.setNombre(formBean.getNombre());
				servicioFamilia.update(f);
				
			} catch (Exception e) {			
				e.printStackTrace();
			}
	        redirectAttributes.addFlashAttribute(EXITO_MESSAGE, "Familia guardada/actualizada exitosamente!");
	        return "redirect:/";
	        }       
  }
	    return "redirect:/";
}
@PostMapping("/FamiliaCrear") 
public String guardarFamilia(@Valid @ModelAttribute(FORM_BEAN) BeansFamilia formBean,//POST PARA CREAR
                             BindingResult bindingResult,
                             Model modelo,
                             RedirectAttributes redirectAttributes,
                           
                             @RequestParam String action) throws CheckedException { 
	
if ("actionAceptar".equals(action)) {
   if (bindingResult.hasErrors()) {      
     modelo.addAttribute("formBean", formBean);
     modelo.addAttribute("error", "Por favor, corrige los errores en el formulario.");
     return "/error";
   }else { 	
        try {
            servicioFamilia.save(formBean.toFamilia());
            redirectAttributes.addFlashAttribute("exitoMessage", "Familia Registrada");
            return "redirect:/Familia/FamiliaCrear"; 
        } catch (CheckedException e) {        
            e.printStackTrace();           
            if (e.getMessage() != null && e.getMessage().contains("Ya existe un asistido registrado con el DNI")) {
                redirectAttributes.addFlashAttribute("errorMessage", "Error: Ya existe un asistido registrado con ese DNI, que pertenece a una familia.");
                return "redirect:/Familia/FamiliaCrear"; 
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Ocurrió un error inesperado al guardar la familia.");
                return "redirect:/Familia/FamiliaCrear";  
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            redirectAttributes.addFlashAttribute("errorMessage", "Ocurrió un error inesperado. Por favor, intente de nuevo más tarde.");
            return "redirect:/Familia/FamiliaCrear";
        }
   }	
	
}else if ("actionCancelar".equals(action)) {
      redirectAttributes.addFlashAttribute("infoMessage", "Operación cancelada.");
    return "redirect:/";        
}          
return "redirect:/error";
}
@PostMapping("/inactivar/{nroFamilia}")
public String inactivarFamilia(@PathVariable(NRO_FAMILIA) Long nroFamilia,//POST BORRADO LOGICO
		                           RedirectAttributes redirectAttributes) throws CheckedException {
    try {
        servicioFamilia.logicalErase(nroFamilia);
        redirectAttributes.addFlashAttribute(EXITO_MESSAGE, "Familia inactivada exitosamente!");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Error al inactivar la familia: " + e.getMessage());
        e.printStackTrace();
    }
    return "redirect:/Familia/FamiliaBuscar";
}
@PostMapping("/eliminar/{nroFamilia}")
public String eliminarFamiliaFisicamente(@PathVariable(NRO_FAMILIA) Long nroFamilia,//POST BORRADO FISICO (Solo Administrador) 
		                                       RedirectAttributes redirectAttributes) throws CheckedException {
    // ** Aquí debes implementar tu lógica de verificación de permisos del administrador **
    // Por ahora, solo mostraremos un mensaje.
    boolean esAdministrador = false; // Simula la verificación de permisos

    if (esAdministrador) {
        try {
            // Si la eliminación física de asistidos debe ocurrir en cascada,
            // tu configuración @OneToMany(cascade = CascadeType.ALL) lo manejará
            // cuando elimines la Familia.
            servicioFamilia.deleteFamilia(nroFamilia); // Este método borraría físicamente
            redirectAttributes.addFlashAttribute(EXITO_MESSAGE, "Familia eliminada físicamente de la base de datos.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Error al eliminar físicamente la familia: " + e.getMessage());
            e.printStackTrace();
        }
    } else {
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "No tienes permisos suficientes para realizar esta acción (Eliminación física de familia).");
    }
    return "redirect:/FamiliaBuscar";
}
@PostMapping("/activar/{nroFamilia}")
public String activarFamilia(@PathVariable(NRO_FAMILIA) Long nroFamilia,//POST PARA ACTIVAR FAMILIA 
		                         RedirectAttributes redirectAttributes) throws CheckedException {
    try {
        servicioFamilia.activarFamilia(nroFamilia); 
        redirectAttributes.addFlashAttribute(EXITO_MESSAGE, "Familia reactivada exitosamente!");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Error al reactivar la familia: " + e.getMessage());
        e.printStackTrace();
    }
    return "redirect:/FamiliaBuscar";
 }
/*
@GetMapping("/confirmacion") // Esta es la URL a la que se redirige
public String confirmacionMensaje(Model modelo, @ModelAttribute("exitoMessage") String exitoMessage) {
    if (exitoMessage == null || exitoMessage.isEmpty()) {
        // Si no hay mensaje de éxito (ej: alguien accede directamente), redirigir a un lugar seguro
        return "redirect:/Familia/FamiliaBuscar"; // O tu página principal
    }
    modelo.addAttribute("message", exitoMessage);
    modelo.addAttribute("redirectTo", "/Familia/FamiliaBuscar"); // La URL a la que finalmente se redirigirá
    modelo.addAttribute("delay", 3000); // 3 segundos de retardo (en milisegundos)
    // --- ¡CAMBIO CLAVE AQUÍ! ---
    // Como confirmacion.html está directamente en templates/, la ruta es solo el nombre del archivo (sin extensión)
    return "redirect:/confirmacion";
}*/

}




