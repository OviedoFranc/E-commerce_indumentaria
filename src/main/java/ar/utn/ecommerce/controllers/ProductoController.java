package ar.utn.ecommerce.controllers;
<<<<<<< Updated upstream

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
=======
import ar.utn.ecommerce.repository.ProductoRepository;
import ar.utn.ecommerce.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
>>>>>>> Stashed changes

@RestController
public class ProductoController {

<<<<<<< Updated upstream

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


=======
     @Autowired
     ProductoRepository repositorioProducto;

     @GetMapping(path = {"/Producto"} )
     public Page<Producto> producto(Pageable page){
     return repositorioProducto.findAll(page);
     }

     @PostMapping(path = {"/Producto"} )
     public Producto agregarProducto(@RequestBody @Valid Producto producto, BindingResult bindingResult){
          if (bindingResult.hasErrors()){
               throw new IllegalStateException("Error en los datos del producto cargado");
          }
          else return repositorioProducto.save(producto);
     }
>>>>>>> Stashed changes

}
