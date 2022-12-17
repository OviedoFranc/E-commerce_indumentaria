package ar.utn.ecommerce.models.DTO;

import ar.utn.ecommerce.models.Productos.EstadoProducto;
import ar.utn.ecommerce.models.Productos.ProductoPersonalizado;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.core.config.Projection;


@Projection(name = "DTOProductoPersonalizacionResumen", types = {ProductoPersonalizado.class} )
public interface DTOProdPersoResume {

        Number getID();
        String getNombre();
        String getFoto();
        String getDescripcion();
        EstadoProducto getEstadoProducto();
        //String getCategoria();
      //  String getCreador().getNombre();


    /*
    * public DTOProdPersoResumeGet(ProductoPersonalizado productoPersonalizado) {
        this.Id = productoPersonalizado.getID();
        this.nombre = productoPersonalizado.getNombre();
        this.link_foto = productoPersonalizado.getLink_foto();
        this.descripcion = productoPersonalizado.getDescripcion();
        this.estadoProducto = productoPersonalizado.getEstadoProducto();
        this.categoria = productoPersonalizado.getCategoria();
       // this.creador = productoPersonalizado.getCreador().getNombre();
    }
    * */
}
