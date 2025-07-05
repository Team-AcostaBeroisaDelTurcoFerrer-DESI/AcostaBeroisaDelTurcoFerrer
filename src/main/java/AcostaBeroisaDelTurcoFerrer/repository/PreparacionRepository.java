package AcostaBeroisaDelTurcoFerrer.repository;

import AcostaBeroisaDelTurcoFerrer.Entities.Preparacion;
import AcostaBeroisaDelTurcoFerrer.Entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PreparacionRepository extends JpaRepository<Preparacion, Long> {
    boolean existsByRecetaAndFechaCoccion(Receta receta, LocalDate fechaCoccion);
    List<Preparacion> findAllByOrderByFechaCoccionDesc();
}
