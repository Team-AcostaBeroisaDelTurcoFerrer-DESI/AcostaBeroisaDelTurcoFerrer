package AcostaBeroisaDelTurcoFerrer.Controller.FamiliaController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.Service.FamiliaService;

@Controller
@RequestMapping("/Familia/FamiliaBuscar") 
public class ControllerFamiliaBuscar {

private static final String FORM_BEAN = "formBean";
@Autowired

private FamiliaService servicioFamilia;
   	
@GetMapping
public String preparaForm(Model modelo) {	
	
BeansFamiliaBuscar form =  new BeansFamiliaBuscar();
modelo.addAttribute(FORM_BEAN,form);
return "/Familia/FamiliaBuscar";
}
     
@PostMapping
public String procesarBusqueda(
        @RequestParam(value = "tipoBusqueda", required = false) String tipoBusqueda,
        @RequestParam(value = "valorBusquedaNro", required = false) Long valorBusquedaNro,
        @RequestParam(value = "valorBusquedaNombre", required = false) String valorBusquedaNombre,
        @RequestParam(value = "filtroNombre", required = false) String filtroNombre, 
        @RequestParam(value = "ordenFecha", required = false) String ordenFecha,
        Model model) {

    List<Familia> familiasEncontradas = Collections.emptyList();

  
    if ("nroFamilia".equals(tipoBusqueda) && valorBusquedaNro != null) {
        familiasEncontradas = servicioFamilia.buscarFamilias(valorBusquedaNro, null);
    } else if ("nombre".equals(tipoBusqueda) && valorBusquedaNombre != null && !valorBusquedaNombre.trim().isEmpty()) {
        familiasEncontradas = servicioFamilia.buscarFamilias(null, valorBusquedaNombre);
    } else {       
       familiasEncontradas = servicioFamilia.findAllActivas(); 
        model.addAttribute("warningMessage", "Por favor, selecciona un criterio de búsqueda y proporciona un valor.");
    }

  
    List<Familia> familiasFiltradas = familiasEncontradas;

    if (filtroNombre != null && !filtroNombre.trim().isEmpty()) {
        familiasFiltradas = familiasFiltradas.stream()
                .filter(f -> f.getNombre() != null && f.getNombre().toLowerCase().contains(filtroNombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    if (ordenFecha != null && !familiasFiltradas.isEmpty()) {
        if ("asc".equals(ordenFecha)) {
            familiasFiltradas.sort(Comparator.comparing(Familia::getFechaRegistro, Comparator.nullsLast(Comparator.naturalOrder())));
        } else if ("desc".equals(ordenFecha)) {
            familiasFiltradas.sort(Comparator.comparing(Familia::getFechaRegistro, Comparator.nullsFirst(Comparator.reverseOrder())));
        }
    }

    model.addAttribute("familias", familiasFiltradas);
  
    model.addAttribute("tipoBusqueda", tipoBusqueda);
    model.addAttribute("valorBusquedaNro", valorBusquedaNro);
    model.addAttribute("valorBusquedaNombre", valorBusquedaNombre);
    model.addAttribute("filtroNombre", filtroNombre);
    model.addAttribute("ordenFecha", ordenFecha);

    return "Familia/FamiliaBuscar"; 
}
}

