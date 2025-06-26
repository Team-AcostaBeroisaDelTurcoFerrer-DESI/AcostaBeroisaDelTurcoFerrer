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
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.UncheckedException;
import AcostaBeroisaDelTurcoFerrer.Service.FamiliaService;

@Controller
@RequestMapping("/verIntegrantes") 
public class ControllerVerIntegrantes {
 @Autowired
    private FamiliaService familiaService; // Necesitarás el servicio de Familia para obtener la entidad

    /**
     * Maneja las solicitudes GET para ver los integrantes de una familia específica.
     * URL esperada: /verIntegrantes/{nroFamilia}
     *
     * @param nroFamilia El ID de la familia cuyos integrantes se desean ver.
     * @param modelo     El ModelMap para pasar datos a la vista.
     * @return El nombre de la plantilla Thymeleaf a renderizar (verIntegrantes.html).
     */
    @GetMapping("/{nroFamilia}") // Aquí solo definimos la parte variable del path
    public String verIntegrantes(@PathVariable("nroFamilia") Long nroFamilia, ModelMap modelo) {
        try {
            // Intenta encontrar la familia por su ID
            Familia familia = (Familia) familiaService.getBynroFamilia(nroFamilia);
			if (familia == null) {
				// Si no se encuentra la familia, lanza una excepción personalizada
				throw new CheckedException("Familia no encontrada con el número: " + nroFamilia);
			}
            // Agrega la familia al modelo para que la vista Thymeleaf pueda acceder a sus datos
            modelo.addAttribute("familia", familia);
           for (Asistido asistido : familia.getAsistidos()) {
                // Agrega cada asistido a la lista de integrantes en el modelo
                modelo.addAttribute("asistidos", asistido);
            }  
            
            // --- ¡Aquí es donde colocarías el código para imprimir en consola! ---
            System.err.println("--- Integrantes de la Familia " + familia.getNombre() + " (ID: " + familia.getNroFamilia() + ") ---");
            if (familia.getAsistidos() != null && !familia.getAsistidos().isEmpty()) {
                for (int i = 0; i < familia.getAsistidos().size(); i++) {
                    System.err.println("Asistido " + (i + 1) + ": " +
                                       familia.getAsistidos().get(i).getNombre() + " " +
                                       familia.getAsistidos().get(i).getApellido() +
                                       " (DNI: " + familia.getAsistidos().get(i).getDni() + ")" +
                                       " (Fecha Registro: " + familia.getAsistidos().get(i).getFechaRegistro() + ")"); // Agrega DNI y FechaRegistro para más detalles
                }
            } else {
                System.err.println("La familia no tiene asistidos registrados.");
            }
            System.err.println("--------------------------------------------------");
            // --- Fin del bloque de impresión ---
            


            // Retorna el nombre de la plantilla Thymeleaf (ej., src/main/resources/templates/verIntegrantes.html)
            return "verIntegrantes";
        } catch (UncheckedException e) {
            // Captura tu excepción personalizada para errores de negocio como "no encontrado"
            modelo.addAttribute("error", e.getMessage());
            // Puedes redirigir a una página de error o a la página de búsqueda
            return "error"; // Asegúrate de tener una plantilla errorPage.html o redirige a otro lugar
        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada
            modelo.addAttribute("error", "Ocurrió un error inesperado: " + e.getMessage());
            return "error";
        }
    }
}
