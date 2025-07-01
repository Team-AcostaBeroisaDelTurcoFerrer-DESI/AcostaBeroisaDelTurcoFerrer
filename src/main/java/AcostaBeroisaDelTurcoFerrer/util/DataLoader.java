package AcostaBeroisaDelTurcoFerrer.util;
import AcostaBeroisaDelTurcoFerrer.Entities.Asistido;
import AcostaBeroisaDelTurcoFerrer.Entities.Familia;
//import AcostaBeroisaDelTurcoFerrer.Service.AsistidoService;
import AcostaBeroisaDelTurcoFerrer.Service.FamiliaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner; // Interfaz para ejecutar código al inicio de Spring Boot
import org.springframework.stereotype.Component; // Para que Spring la detecte como un bean

import java.time.LocalDate;
import java.util.Random;

@Component // Marca esta clase como un componente de Spring para que pueda ser inyectada y gestionada
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FamiliaService familiaService; // Inyectamos el servicio de Familia
    
    // No necesitamos inyectar AsistidoService directamente aquí si Familia.addAsistidos()
    // y familiaService.save() manejan el guardado en cascada de Asistidos.
    // Si tu AsistidoService tiene lógica de validación de DNI que DEBE ejecutarse,
    // entonces deberías inyectarlo y usarlo para guardar cada Asistido.
    // Por simplicidad y asumiendo que CascadeType.ALL es suficiente, lo omito por ahora.
    // @Autowired
    // private AsistidoService asistidoService;

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
    private long dniCounter = 30000000L; // Para generar DNIs únicos

    // Este método se ejecuta automáticamente cuando la aplicación Spring Boot arranca
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Cargando datos de prueba...");
        cargarDatosDePrueba(20, 4); // Carga 20 familias, cada una con 4 integrantes
        System.out.println("Datos de prueba cargados exitosamente.");
    }

    public void cargarDatosDePrueba(int numFamilias, int numIntegrantesPorFamilia) {
        for (int i = 1; i <= numFamilias; i++) {
            Familia familia = new Familia();
            familia.setNombre("Familia " + APELLIDOS_COMUNES[random.nextInt(APELLIDOS_COMUNES.length)] + " ");
            familia.setFechaRegistro(LocalDate.now());

            // Crear y agregar integrantes a la familia
            for (int j = 1; j <= numIntegrantesPorFamilia; j++) {
                Asistido asistido = new Asistido();
                asistido.setNombre(j % 2 == 0 ? NOMBRES_FEMENINOS[random.nextInt(NOMBRES_FEMENINOS.length)] : NOMBRES_MASCULINOS[random.nextInt(NOMBRES_MASCULINOS.length)]);
                asistido.setApellido(APELLIDOS_COMUNES[random.nextInt(APELLIDOS_COMUNES.length)]);
                asistido.setDni(dniCounter++); // Generar DNI único
                asistido.setFechaRegistro(LocalDate.now());
                
                // Si tienes un campo para "jefe de familia" en Asistido, aquí lo manejarías
                // asistido.setJefeDeFamilia(j == 1); // El primer integrante es el jefe

                familia.addAsistidos(asistido); // Usa tu método addAsistidos para bidireccionalidad
            }
            
            // Guardar la familia. Si Familia tiene CascadeType.ALL en la relación OneToMany con Asistido,
            // esto guardará los asistidos automáticamente.
            familiaService.save(familia); 
            System.out.println("Familia '" + familia.getNombre() + "' con " + familia.getAsistidos().size() + " integrantes guardada.");
        }
    }
}