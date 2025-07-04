package AcostaBeroisaDelTurcoFerrer.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AcostaBeroisaDelTurcoFerrer.DAO.IngredienteRepository;
import AcostaBeroisaDelTurcoFerrer.DAO.RecetaRepository;
import AcostaBeroisaDelTurcoFerrer.DTO.IngredienteRecetaDTO;
import AcostaBeroisaDelTurcoFerrer.DTO.RecetaDTO;
import AcostaBeroisaDelTurcoFerrer.DTO.RecetaListadoDTO;
import AcostaBeroisaDelTurcoFerrer.Entities.Ingredientes;
import AcostaBeroisaDelTurcoFerrer.Entities.ItemsReceta;
import AcostaBeroisaDelTurcoFerrer.Entities.Receta;
@Service
public class RecetaServiceImp implements RecetaService {
	@Autowired
	private RecetaRepository recetaRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Override
	public Receta updateReceta(RecetaDTO recetaDTO, Long id) {
		Receta receta = recetaRepository.findByIdAndIsActiveTrue(id)
				.orElseThrow(()->new RuntimeException("Receta no encontrada"));
		
		receta.setDescripcion(recetaDTO.getDescripcion());
		List<ItemsReceta> existentes = receta.getItemsRecetas().stream()
				.filter(ItemsReceta::isActive)
				.toList();
		for(ItemsReceta ir : existentes) {
			ir.setActive(false);
		}
		
		List<ItemsReceta> nuevos = new ArrayList<>();
		for (IngredienteRecetaDTO irDTO : recetaDTO.getIngredientes()) {
			Ingredientes ingrediente = ingredienteRepository.findById(irDTO.getIngredienteId())
					.orElseThrow(()-> new RuntimeException("Ingrediente no encontrado: " + irDTO.getIngredienteId()));
			ItemsReceta nuevo = new ItemsReceta();
			nuevo.setIngrediente(ingrediente);
			nuevo.setCantidad(irDTO.getCantidad());
			nuevo.setCalorias(irDTO.getCalorias());
			nuevo.setActive(true);
			nuevo.setReceta(receta);
			nuevos.add(nuevo);
		}
		
		receta.getItemsRecetas().addAll(nuevos);
		return recetaRepository.save(receta);
	}

	@Override
	public Receta saveReceta(RecetaDTO recetaDTO) {
		if(recetaRepository.findByNombre(recetaDTO.getNombre()).isPresent()) {
			throw new IllegalArgumentException("Ya existe una receta con ese nombre");
		}
		
		Receta receta = new Receta();
		receta.setNombre(recetaDTO.getNombre());
		receta.setDescripcion(recetaDTO.getDescripcion());
		
		Set<ItemsReceta> ingredientes = new HashSet<>();
		
		for (IngredienteRecetaDTO irDTO : recetaDTO.getIngredientes()) {
			Ingredientes ingrediente = ingredienteRepository.findById(irDTO.getIngredienteId())
					.orElseThrow(()-> new RuntimeException("Ingrediente no encontrado: "+irDTO.getIngredienteId()));
			ItemsReceta ir = new ItemsReceta();
			ir.setIngrediente(ingrediente);
			ir.setCantidad(irDTO.getCantidad());
			ir.setCalorias(irDTO.getCalorias());
			ir.setReceta(receta);
			
			ingredientes.add(ir);
		}
		receta.getItemsRecetas().addAll(ingredientes);

		return recetaRepository.save(receta);
	}

	@Override
	public List<RecetaListadoDTO> getReceta(String nombre, Integer minCalorias, Integer maxCalorias) {
		List<Receta> recetas = recetaRepository.findAll()
		        .stream()
		        .filter(r -> r.isActive())
		        .filter(r -> nombre == null || r.getNombre().toLowerCase().contains(nombre.toLowerCase()))
		        .toList();

		    List<RecetaListadoDTO> resultado = new ArrayList<>();

		    for (Receta receta : recetas) {
		        int totalCalorias = receta.getItemsRecetas().stream()
		            .mapToInt(ItemsReceta::getCalorias)
		            .sum();

		        if ((minCalorias == null || totalCalorias >= minCalorias) &&
		            (maxCalorias == null || totalCalorias <= maxCalorias)) {
		            
		            resultado.add(new RecetaListadoDTO(receta.getNombre(), totalCalorias));
		        }
		    }
		return resultado;
	}

	@Override
	public Receta deleteReceta(Long id) {
	    Receta receta = recetaRepository.findByIdAndIsActiveTrue(id)
	            .orElseThrow(() -> new RuntimeException("Receta no encontrada o ya eliminada"));

	    receta.setActive(false);
	    return recetaRepository.save(receta);
	}

}
