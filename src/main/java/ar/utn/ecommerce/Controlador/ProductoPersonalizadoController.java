package ar.utn.ecommerce.Controlador;

import ar.utn.ecommerce.Modelos.DTO.DTOPersonalizacionPost;
import ar.utn.ecommerce.Modelos.DTO.DTOProdPersoResume;
import ar.utn.ecommerce.Modelos.Productos.Personalizacion;
import ar.utn.ecommerce.Modelos.Productos.ProductoBase;
import ar.utn.ecommerce.Modelos.Productos.ProductoPersonalizado;
import ar.utn.ecommerce.Modelos.DTO.DTOProdPersoPost;
import ar.utn.ecommerce.Modelos.Usuario.Vendedor;
import ar.utn.ecommerce.Repositorio.ProductoBaseRepository;
import ar.utn.ecommerce.Repositorio.ProductoPersonalizadoRepository;
import ar.utn.ecommerce.Repositorio.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static ar.utn.ecommerce.Modelos.Productos.EstadoProducto.DISPONIBLE;

@RestController
@CrossOrigin
public class ProductoPersonalizadoController{
    @Autowired
    ProductoPersonalizadoRepository repositorioProductoPersonalizado;
    @Autowired
    ProductoBaseRepository repositorioProductoBase;
    @Autowired
    VendedorRepository repositorioVendedor;

    @GetMapping(path = {"/productoPersonalizado"} )
    public Page<List<DTOProdPersoResume>> productoPersonalizado(@RequestParam(name="categoria",required = false) String categoria,
                                                          Pageable page){
        return repositorioProductoPersonalizado.findAllByEstadoProducto(DISPONIBLE,page);
    }


    @GetMapping(path = {"/productoPersonalizado/{Id}"} )
    public ResponseEntity<ProductoPersonalizado> productoPersonalizadoUnico(@PathVariable Integer Id){

        Optional<ProductoPersonalizado>  productoPersonalizadoTraido = repositorioProductoPersonalizado.findById(Id);

        if (productoPersonalizadoTraido.isEmpty()){
            return ResponseEntity.notFound().build(); // Solo me da un 404
        }
        return new ResponseEntity<>(productoPersonalizadoTraido.get(), HttpStatus.OK);

    }

    @GetMapping(path = {"/productoPersonalizado/{id}/personalizacion"} )
    public ResponseEntity< List <Personalizacion> > obtenerPersonalizacionesProdPersonalizado(@PathVariable Integer id){
        Optional<ProductoPersonalizado> productoPersoTraido = repositorioProductoPersonalizado.findById(id);
        if (productoPersoTraido.isEmpty()){
            return ResponseEntity.notFound().build(); // Solo me da un 404
        }
        return new ResponseEntity<>(productoPersoTraido.get().getPersonalizaciones(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(path = {"/productoPersonalizado"} )
    public void agregarProductoPersonalizado(@RequestBody DTOProdPersoPost dtoProdPersoPost, BindingResult bindingResult){

        Optional<ProductoBase> productoReferenciado = repositorioProductoBase.findById( dtoProdPersoPost.getProductoReferenciadoId());
        Optional<Vendedor> vendedor = repositorioVendedor.findById(dtoProdPersoPost.getVendedorReferenciadoId());

        if (bindingResult.hasErrors()){
            throw new IllegalStateException("Error en los datos del producto cargado");
        }
        else if (productoReferenciado.isEmpty() || vendedor.isEmpty()){
                throw new IllegalStateException("Error el producto/vendedor referenciado no existe");
        }
        else {
            ProductoPersonalizado nuevoProdPersonalizado = new ProductoPersonalizado( dtoProdPersoPost.getNombreProductoPersonalizado(),productoReferenciado.get(),vendedor.get());
            repositorioProductoPersonalizado.save(nuevoProdPersonalizado);
        }

    }

    @Transactional
    @PostMapping(path = {"/productoPersonalizado/{id}/personalizacion"} )
    public ResponseEntity agregarPersonalizacionProductoPersonalizado(@RequestBody @Valid DTOPersonalizacionPost nuevaPersonalizacion,
                                                            BindingResult bindingResult,
                                                            @PathVariable Integer id){
       Optional<ProductoPersonalizado> productoPersonalizado = repositorioProductoPersonalizado.findById(id);
        if (bindingResult.hasErrors()){
            throw new IllegalStateException("Error en los datos del producto cargado");
        }
        else if(productoPersonalizado.isEmpty()){
            throw new IllegalStateException("Error el producto no existe");
        }
        else {
                ProductoBase productoReferenciado = repositorioProductoBase.findById(productoPersonalizado.get().getProductoBaseReferenciado().getID()).get();
                Personalizacion personalizacionNueva = new Personalizacion(nuevaPersonalizacion.getNombre(),
                    nuevaPersonalizacion.getPrecio(),
                    nuevaPersonalizacion.getSectorPersonalizacion(),
                    nuevaPersonalizacion.getTipoPersonalizacion());

                if(!productoReferenciado.getEstadoProducto().equals(DISPONIBLE)) { throw new IllegalStateException("Error el producto referenciado no esta disponible");}
                else {

                    productoPersonalizado.get().addPersonalizacion(personalizacionNueva);
                    return new ResponseEntity<>(productoPersonalizado.get(), HttpStatus.OK);
                }
            //TODO HACER COMPROBACION DE SI POSEE LA SECCION - TIRA ERROR
        }
    }

    @Transactional
    @DeleteMapping(path = {"/productoPersonalizado/{Id}"} )
    public void deleteProductoPersonalizado(@PathVariable Integer Id){

        Optional<ProductoPersonalizado> productoPersonalizado = repositorioProductoPersonalizado.findById(Id);

        if (productoPersonalizado.isEmpty()){
            throw new IllegalStateException("Error el producto personalizado no existe");
        }
        else {
            productoPersonalizado.get().darDeBajaProducto();
            repositorioProductoPersonalizado.save(productoPersonalizado.get());
        }
    }
    @Transactional
    @DeleteMapping(path = {"/productoPersonalizado/{Id}/{personalizacion}"} )
    public void deleteProductoPersonalizado(@PathVariable Integer Id,@PathVariable Integer personalizacion){

        Optional<ProductoPersonalizado> productoPersonalizado = repositorioProductoPersonalizado.findById(Id);

        if (productoPersonalizado.isEmpty()){
            throw new IllegalStateException("Error el producto personalizado no existe");
        }
        else {
            productoPersonalizado.get().darDeBajaProducto();
            repositorioProductoPersonalizado.save(productoPersonalizado.get());
        }
    }
}

