package AcostaBeroisaDelTurcoFerrer.util;
import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
import AcostaBeroisaDelTurcoFerrer.ExceptionPersonal.CheckedException;
//import AcostaBeroisaDelTurcoFerrer.Service.AsistidoService;
import AcostaBeroisaDelTurcoFerrer.Service.FamiliaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner; // Interfaz para ejecutar código al inicio de Spring Boot
import org.springframework.stereotype.Component; // Para que Spring la detecte como un bean

import java.time.LocalDate;
import java.util.Random;

@Component 
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FamiliaService familiaService; 
    
    private static final String[] APELLIDOS_COMUNES = {
        "García", "Rodríguez", "González", "Fernández", "López", "Martínez", "Díaz", "Pérez",
        "Sánchez", "Romero", "Torres", "Ruiz", "Ramírez", "Flores", "Benítez", "Medina",
        "Silva", "Castro", "Ortiz", "Moreno"
    };
    private static final String[] NOMBRES_MASCULINOS = {
        "Juan", "Pedro", "Carlos", "Pablo", "Diego", "Luis", "José", "Miguel", "David", "Javier"
    };
    private static final String[] NOMBRES_FEMENINOS = {
        "María", "Ana", "Laura", "Sofía", "Lucía", "Elena", "Paula", "Carla", "Andrea", "Valeria"
    };

    private Random random = new Random();
    private long dniCounter = 30000000L; 

 
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Cargando datos de prueba...");
        cargarDatosDePrueba(20, 4); 
        System.out.println("Datos de prueba cargados exitosamente.");
    }

    public void cargarDatosDePrueba(int numFamilias, int numIntegrantesPorFamilia) throws CheckedException {
        for (int i = 1; i <= numFamilias; i++) {
            Familia familia = new Familia();
            familia.setNombre("Familia " + APELLIDOS_COMUNES[random.nextInt(APELLIDOS_COMUNES.length)] + " ");
            familia.setFechaRegistro(LocalDate.now());

            for (int j = 1; j <= numIntegrantesPorFamilia; j++) {
                Asistido asistido = new Asistido();
                asistido.setNombre(j % 2 == 0 ? NOMBRES_FEMENINOS[random.nextInt(NOMBRES_FEMENINOS.length)] : NOMBRES_MASCULINOS[random.nextInt(NOMBRES_MASCULINOS.length)]);
                asistido.setApellido(APELLIDOS_COMUNES[random.nextInt(APELLIDOS_COMUNES.length)]);
                asistido.setDni(dniCounter++);
                asistido.setFechaRegistro(LocalDate.now());
                
              
                familia.addAsistidos(asistido); 
            }
            
            
            familiaService.save(familia); 
            System.out.println("Familia '" + familia.getNombre() + "' con " + familia.getAsistidos().size() + " integrantes guardada.");
        }
    }
}