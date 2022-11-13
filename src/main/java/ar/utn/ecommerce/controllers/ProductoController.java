package ar.utn.ecommerce.controllers;

import ar.utn.ecommerce.models.Personalizacion;
import ar.utn.ecommerce.models.Producto;
import ar.utn.ecommerce.models.SectorPersonalizacion;
import ar.utn.ecommerce.models.TipoPersonalizacion;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProductoController {


    @RequestMapping(value = "/producto/{id}")
    public Producto getProducto(@PathVariable Integer id) {
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre("remera");
        producto.setDescripcion("remera de algodon");
        producto.setPrecio_base(20.2);
        producto.setTiempo_fabricacion(5);
        producto.setCreador("Martin RR");
        producto.addPersonalizacionesDisponibles(personalizacionesDisponibles());
        return producto;
    }

    @RequestMapping(value = "/personalizaciones")
    public Personalizacion personalizacionesDisponibles() {

        Personalizacion personalizacion = new Personalizacion();
        personalizacion.setProducto("remera");
        personalizacion.addPosiblesSectoresPersonalizacion(sectorPersonalizacion());
        return personalizacion;
    }

    @RequestMapping(value = "/tipoPersonalizacion")
    public TipoPersonalizacion tipoPersonalizacion(){

        TipoPersonalizacion personal = new TipoPersonalizacion("Emoji");
        return personal;
    }

    @RequestMapping(value = "/sectorPersonalizacion")
        public SectorPersonalizacion sectorPersonalizacion(){

            SectorPersonalizacion sector = new SectorPersonalizacion();
            sector.setSectorPersonalizacion("Cuello");
            sector.addTipoPersonalizacion( new TipoPersonalizacion("imagen blanca"));
            return sector;
        }



}
