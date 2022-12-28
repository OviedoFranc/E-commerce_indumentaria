package ar.utn.ecommerce;

import ar.utn.ecommerce.Modelos.Usuario.MedioPago;
import ar.utn.ecommerce.Modelos.Usuario.UsuarioCorriente;
import ar.utn.ecommerce.Modelos.Usuario.Vendedor;
import ar.utn.ecommerce.Repositorio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;
import java.util.List;
import static ar.utn.ecommerce.Modelos.Usuario.MedioPago.*;


@SpringBootApplication
public class ECommerceApplication {




	private static Logger LOG = LoggerFactory.getLogger(ECommerceApplication.class);

	@Autowired
	ProductoPersonalizadoRepository repositorioProductoPersonalizado;
	@Autowired
	ProductoBaseRepository repositorioProductoBase;
	@Autowired
	PersonalizacionesRepository repositorioPersonalizacion;
	@Autowired
	VendedorRepository repositorioVendedor;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);


	}
	@Bean
	public CommandLineRunner init() {
		LOG.info("arrancando!!");
		return (args) -> {



			List<MedioPago> mediosDePago = Arrays.asList(TARJETADEBITO, TARJETACREDITO, EFECTIVO, BITCOIN);

			Vendedor vendedor = new Vendedor("Juan",
					"juanfalso123@gmail.com",
					"1234",
					"https://cdn.pixabay.com/photo/2022/11/20/15/47/portrait-7604619_960_720.jpg", "Juan es un joven creador de moda de 27 años con una pasión por el diseño de ropa y un talento innato para la moda. Su creatividad y habilidades lo han llevado a convertirse en uno de los diseñadores más prometedores de su generación.",
					mediosDePago);


			repositorioVendedor.save(vendedor);

			List<MedioPago> mediosDePago2 = Arrays.asList(TARJETADEBITO, EFECTIVO);
			Vendedor vendedor2 = new Vendedor("Marta",
					"martafalso123@gmail.com",
					"1234",
					"https://cdn.pixabay.com/photo/2022/04/29/14/28/woman-7163866_960_720.jpg",
					"Marta es una experimentada creadora de moda con años de experiencia en el mundo de la moda. Su enfoque en la sostenibilidad y la inclusividad en su trabajo la han llevado a ser reconocida como una de las diseñadoras más prometedoras de su generación. ",
					mediosDePago2);
			repositorioVendedor.save(vendedor2);

			List<MedioPago> mediosDePago3 = Arrays.asList(TARJETADEBITO,TARJETACREDITO);
			Vendedor vendedor3 = new Vendedor("Bob",
					"bobfalso123@gmail.com",
					"1234",
					"https://cdn.pixabay.com/photo/2022/10/17/15/02/photography-7527978_960_720.jpg",
					"Bob es un creador de moda muy creativo e innovador. Con un talento natural para el diseño de ropa y una visión única de la moda, su creatividad y habilidades lo han llevado a trabajar con algunas de las marcas más reconocidas del mundo.",
					mediosDePago3);
			repositorioVendedor.save(vendedor3);



//-------------------------------------------------------------------------

			UsuarioCorriente comprador = new UsuarioCorriente("compradorFalso", "compradorFalso@gmail.com", "1234");
			usuarioRepository.save(comprador);




		};
	}
}

