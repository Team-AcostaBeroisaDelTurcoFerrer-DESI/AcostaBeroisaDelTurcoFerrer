package AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;
import AcostaBeroisaDelTurcoFerrer.Service.FamiliaService;

@Controller
@RequestMapping("/Familia/verIntegrantes") 
public class ControllerIntegrantes {
@Autowired
private FamiliaService familiaService; 

@GetMapping("/{nroFamilia}")
public String verIntegrantes(@PathVariable("nroFamilia") Long nroFamilia, ModelMap modelo) {
  try {           
   Familia familia = (Familia) familiaService.getBynroFamilia(nroFamilia);
     if (familia == null) {			
	    throw new CheckedException("Familia no encontrada con el número: " + nroFamilia);
		}
          
     modelo.addAttribute("familia", familia);
     for (Asistido asistido : familia.getAsistidos()) { 
    	if (asistido.isEstaActiva()==true) {		
    	modelo.addAttribute("asistidos", asistido);	
    	}        
      }                        
  return "Familia/verIntegrantes";
  } catch (CheckedException e) {
    modelo.addAttribute("error", e.getMessage());            
    return "error";
  } catch (Exception e) {            
    modelo.addAttribute("error", "Ocurrió un error inesperado: " + e.getMessage());
    return "error";
  }
 }
}
