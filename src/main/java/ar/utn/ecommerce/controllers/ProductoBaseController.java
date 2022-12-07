package ar.utn.ecommerce.controllers;
import ar.utn.ecommerce.models.Productos.EstadoProducto;
import ar.utn.ecommerce.models.Productos.ProductoBase;
import ar.utn.ecommerce.models.Productos.ProductoPersonalizado;
import ar.utn.ecommerce.models.Productos.SectorPersonalizacion;
import ar.utn.ecommerce.models.projection.DTOProdPersoPost;
import ar.utn.ecommerce.repository.ProductoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static ar.utn.ecommerce.models.Productos.EstadoProducto.*;


@RestController
public class ProductoBaseController {

     @Autowired
     ProductoBaseRepository repositorioProducto;

     @GetMapping(path = {"/productoBase"} )
     public Page<ProductoBase> productos(Pageable page) {
          return repositorioProducto.findByEstadoProducto(DISPONIBLE,page);
     }


     @GetMapping(path = {"/productoBase/all"} )
     public ProductoBase productos() {
          return repositorioProducto.findByEstadoProducto(DISPONIBLE);
     }

     @GetMapping(path = {"/productoBase/{id}"} )
     public ResponseEntity<ProductoBase> producto(@PathVariable Integer id){

          Optional<ProductoBase> productoTraido = repositorioProducto.findById(id) ;
          if (productoTraido.isEmpty()){
               return ResponseEntity.notFound().build(); // Solo me da un 404
          }
          return new ResponseEntity<>(productoTraido.get(), HttpStatus.OK);
     }

     @GetMapping(path = {"/productoBase/{id}/seccion"} )   //TODO: Comprobar si el ID existe
     public List<SectorPersonalizacion> seccionesProducto(Pageable page, @PathVariable Integer id){
          return repositorioProducto.findById(id).get().getSectoresPersonalizacionDisponibles();   //  TODO: control de errores si esta vacio
     }


     @Transactional
     @PostMapping(path = {"/productoBase"} )
     public void agregarProducto(@RequestBody @Valid ProductoBase ProductoBase, BindingResult bindingResult){
          if (bindingResult.hasErrors()){
               throw new IllegalStateException("Error en los datos del producto cargado");
          }
          else  repositorioProducto.save(ProductoBase);
     }

     @Transactional
     @PostMapping(path = {"/productoBase/{id}/seccion"} )
     public void agregarSeccion(@RequestBody @Valid SectorPersonalizacion SectorPersonalizacion,@PathVariable Integer id , BindingResult bindingResult){
          if (bindingResult.hasErrors()){
               throw new IllegalStateException("Error en los datos de la seccion del producto cargado");
          }

          else {
               ProductoBase producto = repositorioProducto.findById(id).get();
               producto.addSectorPersonalizacion(SectorPersonalizacion);
               repositorioProducto.save(producto);
          }
     }
     @Transactional
     @PostMapping(path = {"/productoBase/{id}/{seccionId}/tipoPersonalizacion"} )// Comprobar si el ID existe  // control de errores si esta vacio
     public void agregarTipoPersonalizacion(@RequestBody @Valid String tipoPersonalizacion, @PathVariable Integer id, @PathVariable Integer seccionId,BindingResult bindingResult){
          if (bindingResult.hasErrors()){
               throw new IllegalStateException("Error en los datos del tipo de personalizacion cargado");
          }

          else {
               ProductoBase producto = repositorioProducto.findById(id).get();
               producto.getSectoresPersonalizacionDisponibles().stream().filter( sector -> sector.getSectorID().equals(seccionId)).findFirst().get().addposiblesTipoPersonalizacion(tipoPersonalizacion);
               repositorioProducto.save(producto);
          }
     }

     // TODO: LOS DELETE
}
