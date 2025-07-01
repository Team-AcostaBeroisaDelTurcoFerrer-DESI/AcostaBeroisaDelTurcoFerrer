package AcostaBeroisaDelTurcoFerrer.Controller.HomeController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Esta anotación marca la clase como un controlador de Spring MVC
public class HomeController {

  
    @GetMapping("/") // Mapea la URL raíz del sitio
    public String homePage(Model model) {
       return "index"; 
    }
/* 
    @GetMapping("/confirmacion") 
    public String confirmacionPage() {
        
        return "confirmacion"; 
    }
   
   
    @GetMapping("/FamiliaCRUD") 
    public String buscarCRUDPage() {
        
        return "FamiliaCRUD"; 
    }
  */  
    
    
}