package ar.utn.ecommerce;

import ar.utn.ecommerce.Modelos.Productos.*;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static ar.utn.ecommerce.Modelos.Productos.Categoria.*;
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


			List<String> posiblesPersonalizaciones = new ArrayList<>();
			posiblesPersonalizaciones.add("Estampado");
			posiblesPersonalizaciones.add("Frase");
			posiblesPersonalizaciones.add("Emoji");
			SectorPersonalizacion sectorPersonalizacion = new SectorPersonalizacion("Frente Delantero", posiblesPersonalizaciones);


			ProductoBase productoNuevo = new ProductoBase("Remera",
					"https://apositivo.store/wp-content/uploads/2021/12/IMG_0026-350x350.jpg",
					"Remera basica de poliester conm un corte ingles",
					500.00,
					5,
					REMERA);
			productoNuevo.addSectorPersonalizacion(sectorPersonalizacion);
			List<MedioPago> mediosDePago = Arrays.asList(TARJETADEBITO, TARJETACREDITO, EFECTIVO, BITCOIN);

			Vendedor vendedor = new Vendedor("Juan",
					"juanfalso123@gmail.com",
					"1234",
					"https://cdn.pixabay.com/photo/2022/11/20/15/47/portrait-7604619_960_720.jpg", "Juan es un joven creador de moda de 27 años con una pasión por el diseño de ropa y un talento innato para la moda. Su creatividad y habilidades lo han llevado a convertirse en uno de los diseñadores más prometedores de su generación.",
					mediosDePago);

			repositorioProductoBase.save(productoNuevo);

			repositorioVendedor.save(vendedor);


			ProductoPersonalizado productoPersonalizado = new ProductoPersonalizado("Calipso Buzo", productoNuevo, vendedor);
			repositorioProductoPersonalizado.save(productoPersonalizado);


			Personalizacion nuevaPersonalizacion = new Personalizacion("Frase posterior Linda",
					75.00,
					"Parte Posterior",
					"Frase ");

			productoPersonalizado.addPersonalizacion(nuevaPersonalizacion);
			repositorioProductoPersonalizado.save(productoPersonalizado);


//-------------------------------------------------------------------------

			UsuarioCorriente comprador = new UsuarioCorriente("compradorFalso", "compradorFalso@gmail.com", "1234");
			usuarioRepository.save(comprador);


/*			ProductoPersonalizadoCompra productoUnoComprado = new ProductoPersonalizadoCompra(productoPersonalizado, nuevaPersonalizacion);
			repositorioPersonalizacion.findById(nuevaPersonalizacion.getID());


			List<ProductoPersonalizadoCompra> productosComprados =  new ArrayList<>();

			productosComprados.add(
					new ProductoPersonalizadoCompra(
							repositorioProductoPersonalizado.findById(productoPersonalizado.getID()).get() ,
							repositorioPersonalizacion.findById(nuevaPersonalizacion.getID()).get()
								));


			Venta ventaNueva = new Venta(comprador, productosComprados);
			comprador.addCompra(ventaNueva);
			usuarioRepository.save(comprador);*/
			System.out.println("TARAN");
/*
			comprador.comprar(productos);
			usuarioRepository.save(comprador);


				if(ProductoPersonalizado.get().permitirPersonalizacion(nuevaPersonalizacion)){
					System.out.print("el producto permite esta personalizacion");
				}
				else System.out.print("el producto No permite esta personalizacion");
*/

				//if(!productoReferenciado.getEstadoProducto().equals(DISPONIBLE)) { throw new IllegalStateException("Error el producto referenciado no esta disponible");}


/*
				if (!productoReferenciado.poseeSectorPersonalizacion(sectorBuscado) ) System.out.print(" el sector buscado se encuentra en el referenciado");
					else System.out.print("EEEE ERROR");

					 &&
						!sectoresPersonalizacionPosibles.stream().anyMatch( sector -> sector.poseeTipoPersonalizacion( personalizacionBuscada) ) ){


					System.out.print(" Producto referenciado posee el sector buscaro?" +  productoReferenciado.poseeSectorPersonalizacion(sectorBuscado) );

					System.out.print(" existe algun sector del prod referenciado que posea el tipo de personalizacion: " +  sectoresPersonalizacionPosibles.stream().anyMatch( sector -> sector.poseeTipoPersonalizacion( personalizacionBuscada) ) );
					}

*/




		};
	}
}

