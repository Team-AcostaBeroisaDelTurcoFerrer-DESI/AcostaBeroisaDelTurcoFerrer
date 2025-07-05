package AcostaBeroisaDelTurcoFerrer.Service;

import AcostaBeroisaDelTurcoFerrer.DTO.PreparacionDTO;
import AcostaBeroisaDelTurcoFerrer.Entities.*;
import AcostaBeroisaDelTurcoFerrer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PreparacionService {

    @Autowired
    private PreparacionRepository preparacionRepository;
    
    @Autowired
    private RecetaRepository recetaRepository;

    public List<Preparacion> listarTodas() {
        return preparacionRepository.findAllByOrderByFechaCoccionDesc();
    }

    public boolean existePreparacionParaFecha(Receta receta, LocalDate fecha) {
        return preparacionRepository.existsByRecetaAndFechaCoccion(receta, fecha);
    }

    public void guardar(PreparacionDTO preparacionDto) {
    	Receta receta = recetaRepository.findById(preparacionDto.getRecetaId()).orElse(null);
        if (existePreparacionParaFecha(receta, preparacionDto.getFechaCoccion())) {
            throw new RuntimeException("Ya existe una preparación para esta receta en esa fecha.");
        }
        Preparacion preparacion = new Preparacion();
        preparacion.setReceta(receta);
        preparacion.setFechaCoccion(preparacionDto.getFechaCoccion());
        preparacion.setTotalRacionesPreparadas(preparacionDto.getTotalRacionesPreparadas());
        preparacion.setStockRacionesRestantes(preparacion.getTotalRacionesPreparadas());
        preparacionRepository.save(preparacion);
    }

    public void eliminar(Long id) {
        preparacionRepository.deleteById(id);
    }

    public Preparacion buscarPorId(Long id) {
        return preparacionRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontrada"));
    }
}
