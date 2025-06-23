package AcostaBeroisaDelTurcoFerrer.Controller.HomeController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Esta anotación marca la clase como un controlador de Spring MVC
public class HomeController {

  
    @GetMapping("/") // Mapea la URL raíz del sitio
    public String homePage(Model model) {
       return "index"; // Esto indica a Thymeleaf que renderice src/main/resources/templates/index.html
    }

    /*@GetMapping("/FamiliaBuscar") // Mapea la URL para buscar familias
    public String buscarFamiliaPage() {
        
        return "familiaBuscar"; // Retorna el nombre de la plantilla (ej. familiaBuscar.html)
    }

    @GetMapping("/AsistidoBuscar") // Mapea la URL para buscar asistidos
    public String buscarAsistidoPage() {
        
        return "asistidoBuscar"; // Retorna el nombre de la plantilla (ej. asistidoBuscar.html)
    }*/
    
    
}