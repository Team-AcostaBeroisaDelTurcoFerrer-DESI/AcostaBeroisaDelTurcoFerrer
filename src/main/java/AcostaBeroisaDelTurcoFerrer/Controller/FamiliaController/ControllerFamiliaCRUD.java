package AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.Service.FamiliaService;


@Controller
@RequestMapping("/FamiliaCRUD")
public class ControllerFamiliaCRUD {
				
@Autowired
private FamiliaService servicioFamilia;

Familia familia = new Familia(); //Instancia de Familia para usar en el controlador
					
@GetMapping("/verIntegrantes/{nroFamilia}") 
public String verIntegrantesFamilia(@PathVariable("nroFamilia") Long nroFamilia, Model model,
		                                     RedirectAttributes redirectAttributes) {
												
familia = servicioFamilia.getBynroFamilia(nroFamilia);
	  
if (familia != null && familia.getNroFamilia() != null) { 			         
	List <Asistido> integrantes = familia.getAsistido();
	model.addAttribute("familia", familia);
	model.addAttribute("integrantes", integrantes);

	return "Familia/verIntegrantes"; 
	} else {
		    redirectAttributes.addFlashAttribute("errorMessage", "La familia solicitada no fue encontrada.");
		    return "redirect:/FamiliaBuscar";
		    }
}
		
		
		   
	    @GetMapping({"", "/{nroFamilia}"})
	    public String preparaForm(Model modelo, @PathVariable("nroFamilia") Optional<Long> nroFamilia) throws Exception {
	    Familia familia= new Familia();
	    	
	    	//Si nroFamilia es null, entonces estoy por crear una nueva provincia
	    	//Si nroFamilia tiene un valor, entonces estoy por editar una provincia existente
			
			if (nroFamilia.isPresent()) 
	    	{
	    		//Estoy por editar una provincia existente
	    		//familia = servicioFamilia.getBynroFamilia(nroFamilia.get());
	    		modelo.addAttribute("formBean",servicioFamilia.getBynroFamilia(nroFamilia.get()));//verificar antes
			} else {				          
            familia.addAsistido(new Asistido());
			modelo.addAttribute("formBean",familia);
			}
	       return "/FamiliaCRUD";
	    }
	     
	    
	    
	    @ModelAttribute("allFamilia")
	    public List<Familia> getAllFamilia() {
	        return this.servicioFamilia.getAll();
	    }
	    
	    
		
		@GetMapping("/delete/{nroFamilia}")
		public String deleteById(Model modelo, @PathVariable("nroFamilia") Long nroFamilia) throws Exception 
		{
			servicioFamilia.deleteBynroFamilia(nroFamilia);//verificar para cambiar mpor nro familia
			return "redirect:/FamiliaBuscar";
		}
	 
	    
	    @PostMapping
	    public String submit(@ModelAttribute("formBean") @Validated  Familia formBean,BindingResult result, ModelMap modelo,@RequestParam String action) {
	    	
	    	
	    	if(action.equals("actionAceptar"))
	    	{
	    		if(result.hasErrors())
	    		{
	    			modelo.addAttribute("formBean",formBean);
	    			 return "/FamiliaCRUD";
	    		}
	    		else
	    		{
	    			try {
	    					  
	    				servicioFamilia.save(formBean);
						
						return "redirect:/";
					} catch (Exception e) {
						
						// Aquí puedes decidir qué hacer si el mensaje de la excepción es null o vacío
					    String errorMessage = e.getMessage();
					    if (errorMessage == null || errorMessage.isEmpty()) {
					        errorMessage = "Ocurrió un error inesperado al buscar la familia."; // Mensaje por defecto
					    
					    ObjectError error = new ObjectError("globalError", errorMessage);
					    result.addError(error);
					    }
						/*if(e.getAtributo()==null) //si la excepcion estuviera referida a un atributo del objeto, entonces mostrarlo al lado del compornente (ej. dni)
						{
							ObjectError error = new ObjectError("globalError", e.getMessage());
				            result.addError(error);	
					    }
						else
						{
				    		FieldError error1 = new FieldError("formBean",e.getAtributo(),e.getMessage());
				            result.addError(error1);

						}*/
			            modelo.addAttribute("formBean",formBean);
		    			return "FamiliaCRUD";  //Como existe un error me quedo en la misma pantalla
					}
	    		}
	    	}
	    	else if(action.equals("actionCancelar"))
	    	{
	    		modelo.clear();
	    		return "redirect:/";
	    	}
	    		
	    	return "redirect:/FamiliaBuscar";//?????
	    	
	    	
	    }

	 
	}


