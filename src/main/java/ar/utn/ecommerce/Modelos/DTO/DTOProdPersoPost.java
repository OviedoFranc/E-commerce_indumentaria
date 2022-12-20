package ar.utn.ecommerce.Modelos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DTOProdPersoPost {

    String nombreProductoPersonalizado;
    Integer productoReferenciadoId;
    Integer vendedorReferenciadoId;

    public DTOProdPersoPost(String nombreProductoPersonalizado, Integer productoReferenciadoId, Integer vendedorReferenciadoId) {
        this.nombreProductoPersonalizado = nombreProductoPersonalizado;
        this.productoReferenciadoId = productoReferenciadoId;
        this.vendedorReferenciadoId = vendedorReferenciadoId;
    }
}
