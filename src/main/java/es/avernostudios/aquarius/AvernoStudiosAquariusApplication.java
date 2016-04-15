package es.avernostudios.aquarius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "es.avernostudios.aquarius.bean")
public class AvernoStudiosAquariusApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvernoStudiosAquariusApplication.class, args);
	}
}
