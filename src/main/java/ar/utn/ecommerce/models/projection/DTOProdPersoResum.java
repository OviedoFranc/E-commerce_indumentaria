package ar.utn.ecommerce.models.projection;

import ar.utn.ecommerce.models.Productos.ProductoPersonalizado;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "DTOProdPersoResum", types = {ProductoPersonalizado.class})
public interface DTOProdPersoResum {

}
