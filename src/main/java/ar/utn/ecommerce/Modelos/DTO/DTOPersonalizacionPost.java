package ar.utn.ecommerce.Modelos.DTO;

import ar.utn.ecommerce.Modelos.Productos.ProductoPersonalizado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter

public class DTOPersonalizacionPost {

    String nombre;
    Double precio;
    String sectorPersonalizacion;
    String tipoPersonalizacion;

    public DTOPersonalizacionPost(String nombre, Double precio, String sectorPersonalizacion, String tipoPersonalizacion) {
        this.nombre = nombre;
        this.precio = precio;
        this.sectorPersonalizacion = sectorPersonalizacion;
        this.tipoPersonalizacion = tipoPersonalizacion;
    }
}
