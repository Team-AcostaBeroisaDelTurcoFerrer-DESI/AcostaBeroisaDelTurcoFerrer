package AcostaBeroisaDelTurcoFerrer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("AcostaBeroisaDelTurcoFerrer.Entities") 
public class AcostaBeroisaDelTurcoFerrerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcostaBeroisaDelTurcoFerrerApplication.class, args);
	}

}
