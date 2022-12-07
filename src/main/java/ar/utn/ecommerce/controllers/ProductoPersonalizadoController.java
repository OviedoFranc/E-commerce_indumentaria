package ar.utn.ecommerce.controllers;

import ar.utn.ecommerce.models.Productos.Personalizacion;
import ar.utn.ecommerce.models.Productos.ProductoBase;
import ar.utn.ecommerce.models.Productos.ProductoPersonalizado;
import ar.utn.ecommerce.models.Productos.SectorPersonalizacion;
import ar.utn.ecommerce.models.Usuario.Vendedor;
import ar.utn.ecommerce.models.projection.DTOProdPersoPost;
import ar.utn.ecommerce.repository.ProductoBaseRepository;
import ar.utn.ecommerce.repository.ProductoPersonalizadoRepository;
import ar.utn.ecommerce.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static ar.utn.ecommerce.models.Productos.EstadoProducto.DISPONIBLE;

@RestController
public class ProductoPersonalizadoController{
    @Autowired
    ProductoPersonalizadoRepository repositorioProductoPersonalizado;
    @Autowired
    ProductoBaseRepository repositorioProductoBase;
    @Autowired
    VendedorRepository repositorioVendedor;

    @GetMapping(path = {"/productoPersonalizado"} )
    public Page<ProductoPersonalizado> productoPersonalizado(@RequestParam(name="categoria",required = false) String categoria,
                                                             Pageable page){
        return repositorioProductoPersonalizado.findByEstadoProducto(DISPONIBLE,page);      // TODO: implementar filtro para categoria
    }


    @GetMapping(path = {"/productoPersonalizado/{id}"} )
    public ResponseEntity<ProductoPersonalizado> productoPersonalizadoUnico(@PathVariable Integer id){
        Optional<ProductoPersonalizado> productoPersoTraido = repositorioProductoPersonalizado.findById(id);
        if (productoPersoTraido.isEmpty()){
            return ResponseEntity.notFound().build(); // Solo me da un 404
        }
        return new ResponseEntity<>(productoPersoTraido.get(), HttpStatus.OK);
    }

    @GetMapping(path = {"/productoPersonalizado/{id}/personalizacion"} )
    public ResponseEntity< List <Personalizacion> > obtenerPersonalizacionesProdPersonalizado(@PathVariable Integer id){
        Optional<ProductoPersonalizado> productoPersoTraido = repositorioProductoPersonalizado.findById(id);
        if (productoPersoTraido.isEmpty()){
            return ResponseEntity.notFound().build(); // Solo me da un 404
        }
        return new ResponseEntity<>(productoPersoTraido.get().getPersonalizacion(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(path = {"/productoPersonalizado"} )
    public void agregarProductoPersonalizado(@RequestBody DTOProdPersoPost datosProdPerso, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new IllegalStateException("Error en los datos del producto cargado");
        }

        else {   //TODO : VERIFICAR SI PROD REF Y VENDEDOR NO ESTAN NULL, CUYO CASO LANZAR ERROR

            //TODO: PARTE DE VENDEDORES
            Optional<ProductoBase> productoReferenciado = repositorioProductoBase.findById( datosProdPerso.getProductoReferenciadoId());
           // Optional<Vendedor> vendedor = repositorioVendedor.findById(datosProdPerso.getVendedorReferenciadoId());
            ProductoPersonalizado nuevoProdPersonalizado = new ProductoPersonalizado( datosProdPerso.getNombreProductoPersonalizado(),productoReferenciado.get()/*,vendedor.get() */);
            repositorioProductoPersonalizado.save(nuevoProdPersonalizado);
        }

    }
    @Transactional
    @PostMapping(path = {"/productoPersonalizado/{id}/personalizacion"} )
    public void agregarPersonalizacionProductoPersonalizado(@RequestBody @Valid Personalizacion Personalizacion,
                                                            BindingResult bindingResult,
                                                            @PathVariable Integer id){
        if (bindingResult.hasErrors()){
            throw new IllegalStateException("Error en los datos del producto cargado");
        }
        else    { //TODO CONTROLAR QUE EL PRODUCTO PERSONALIZADO EXISTE Y ESTA DISONIBLE, NO EN PAUSA
            ProductoPersonalizado ProductoPersonalizado = repositorioProductoPersonalizado.findById(id).get();
            ProductoPersonalizado.addPersonalizacion(Personalizacion);
            repositorioProductoPersonalizado.save(ProductoPersonalizado);
        }

    }

}
