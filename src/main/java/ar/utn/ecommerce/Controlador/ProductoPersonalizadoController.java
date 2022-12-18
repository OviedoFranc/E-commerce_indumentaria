package ar.utn.ecommerce.Controlador;

import ar.utn.ecommerce.models.DTO.DTOProdPersoResume;
import ar.utn.ecommerce.models.Productos.Personalizacion;
import ar.utn.ecommerce.models.Productos.ProductoBase;
import ar.utn.ecommerce.models.Productos.ProductoPersonalizado;
import ar.utn.ecommerce.models.DTO.DTOProdPersoPost;
import ar.utn.ecommerce.models.Productos.SectorPersonalizacion;
import ar.utn.ecommerce.models.Usuario.Vendedor;
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

import static ar.utn.ecommerce.models.Productos.EstadoProducto.CANCELADO;
import static ar.utn.ecommerce.models.Productos.EstadoProducto.DISPONIBLE;

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
        else { ProductoPersonalizado nuevoProdPersonalizado = new ProductoPersonalizado( dtoProdPersoPost.getNombreProductoPersonalizado(),productoReferenciado.get(),vendedor.get());
            repositorioProductoPersonalizado.save(nuevoProdPersonalizado);
        }

    }
    @Transactional
    @PostMapping(path = {"/productoPersonalizado/{id}/personalizacion"} )
    public ResponseEntity agregarPersonalizacionProductoPersonalizado(@RequestBody @Valid Personalizacion nuevaPersonalizacion,
                                                            BindingResult bindingResult,
                                                            @PathVariable Integer id){
        Optional<ProductoPersonalizado> ProductoPersonalizado = repositorioProductoPersonalizado.findById(id);
        if (bindingResult.hasErrors()){
            throw new IllegalStateException("Error en los datos del producto cargado");
        }
        else if(ProductoPersonalizado.isEmpty()){
            throw new IllegalStateException("Error el producto no existe");
        }
        else {
                ProductoBase productoReferenciado = ProductoPersonalizado.get().getProductoBaseReferenciado();
                List<SectorPersonalizacion> sectoresPersonalizacionPosibles = productoReferenciado.getSectoresPersonalizacionDisponibles();
                String sectorBuscado = nuevaPersonalizacion.getSectorPersonalizacion();
                String personalizacionBuscada = nuevaPersonalizacion.getSectorPersonalizacion();
                if(!productoReferenciado.getEstadoProducto().equals(DISPONIBLE)) { throw new IllegalStateException("Error el producto referenciado no esta disponible");}
                else if(!productoReferenciado.poseeSectorPersonalizacion(sectorBuscado).equals(null) &&
                   !sectoresPersonalizacionPosibles.stream().anyMatch( sector -> sector.poseeTipoPersonalizacion( personalizacionBuscada) )
                   ){
                    ProductoPersonalizado.get().addPersonalizacion(nuevaPersonalizacion);
                    repositorioProductoPersonalizado.save(ProductoPersonalizado.get());
                }
                else throw new IllegalStateException("Error el producto referenciado no soporta ese sector/tipo personalizacion");
            return new ResponseEntity<>(ProductoPersonalizado.get(), HttpStatus.OK);
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

