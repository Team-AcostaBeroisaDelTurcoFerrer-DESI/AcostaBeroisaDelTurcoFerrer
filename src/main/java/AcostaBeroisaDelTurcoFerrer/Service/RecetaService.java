package AcostaBeroisaDelTurcoFerrer.Service;

import java.util.List;

import AcostaBeroisaDelTurcoFerrer.DTO.RecetaDTO;
import AcostaBeroisaDelTurcoFerrer.DTO.RecetaListadoDTO;
import AcostaBeroisaDelTurcoFerrer.Entities.Receta;

public interface RecetaService {
	Receta updateReceta(RecetaDTO recetaDTO, Long id);
	Receta saveReceta(RecetaDTO recetaDTO);
	List<RecetaListadoDTO> getReceta(String nombre, Integer minCalorias,Integer maxCalorias);
	Receta deleteReceta(Long id);
}
