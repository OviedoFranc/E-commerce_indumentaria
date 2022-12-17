package ar.utn.ecommerce.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.core.config.Projection;

@Getter
@Setter

public class DTOProdPersoPost {

    String nombreProductoPersonalizado;
    Integer productoReferenciadoId;
   // Integer vendedorReferenciadoId;

    public DTOProdPersoPost(String nombreProductoPersonalizado, Integer productoReferenciadoId/*, Integer vendedorReferenciadoId*/) {
        this.nombreProductoPersonalizado = nombreProductoPersonalizado;
        this.productoReferenciadoId = productoReferenciadoId;
        //this.vendedorReferenciadoId = vendedorReferenciadoId;
    }
}
