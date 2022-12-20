package ar.utn.ecommerce.Modelos.DTO;

import ar.utn.ecommerce.Modelos.Productos.Personalizacion;
import ar.utn.ecommerce.Modelos.Productos.ProductoPersonalizado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DTOProdPersoCompra {
    Integer productoPersonalizadoRefID;
    Integer personalizacionRefID;

    public  DTOProdPersoCompra(Integer productoPersonalizadoRefID, Integer personalizacionRefID) {
        this.productoPersonalizadoRefID = productoPersonalizadoRefID;
        this.personalizacionRefID = personalizacionRefID;
    }

}
