package ar.utn.ecommerce;

import ar.utn.ecommerce.repository.ProductoRepository;
import ar.utn.ecommerce.models.Producto;
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
	ProductoRepository repositorioProducto;


	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);

	}
	@Bean
	public CommandLineRunner init() {
		LOG.info("arrancando!!");
		return (args) -> {
			System.out.print(args);

			repositorioProducto.save(new Producto("Camisa cuello", "linkFoto","Camisa basica",50.00,10,"Amssy","Disponible","ropa"));
			repositorioProducto.save(new Producto("Camisa manga", "linkFoto","Camisa basica con manga",200.00,3,"Robert","Disponible","ropa"));

			System.out.print(" cantidad de productos : " + repositorioProducto.count());
			System.out.print(repositorioProducto.findAll());
		};
	}
}

