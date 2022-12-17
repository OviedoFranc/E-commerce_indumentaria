package ar.utn.ecommerce;

import ar.utn.ecommerce.Repositorio.ProductoBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ECommerceApplication {




	private static Logger LOG = LoggerFactory.getLogger(ECommerceApplication.class);

	@Autowired
	ProductoBaseRepository ProductoBaseRepository;


	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);


	}
	@Bean
	public CommandLineRunner init() {
		LOG.info("arrancando!!");
		return (args) -> {
			System.out.print(args);

			System.out.print(" cantidad de productos : " + ProductoBaseRepository.count());
			System.out.print(ProductoBaseRepository.findAll());
		};
	}
}

