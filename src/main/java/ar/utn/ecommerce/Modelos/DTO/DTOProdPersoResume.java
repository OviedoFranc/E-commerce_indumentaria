package ar.utn.ecommerce.Modelos.DTO;

import ar.utn.ecommerce.Modelos.Productos.EstadoProducto;
import ar.utn.ecommerce.Modelos.Productos.ProductoPersonalizado;
import org.springframework.data.rest.core.config.Projection;


@Projection(name = "DTOProductoPersonalizacionResumen", types = {ProductoPersonalizado.class} )
public interface DTOProdPersoResume {

        Number getID();
        String getNombre();
        String getFoto();
        String getDescripcion();
        EstadoProducto getEstadoProducto();

}
