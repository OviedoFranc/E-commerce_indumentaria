package ar.utn.ecommerce.Controlador;
import ar.utn.ecommerce.Modelos.Productos.ProductoBase;
import ar.utn.ecommerce.Modelos.Productos.SectorPersonalizacion;
import ar.utn.ecommerce.Repositorio.ProductoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static ar.utn.ecommerce.Modelos.Productos.EstadoProducto.*;


@RestController
@CrossOrigin
public class ProductoBaseController {

     @Autowired
     ProductoBaseRepository repositorioProducto;

     @GetMapping(path = {"/productoBase"} )
     public List<ProductoBase> productoBaseDisponibles() {
          return repositorioProducto.findAllByEstadoProducto(DISPONIBLE);
     }

     @GetMapping(path = {"/productoBase/all"} )
     public List<ProductoBase> todosLosProductosBase() {
          return repositorioProducto.findAll();
     }

     @GetMapping(path = {"/productoBase/{id}"} )
     public ResponseEntity<ProductoBase> producto(@PathVariable Integer id){
          Optional<ProductoBase> productoTraido = repositorioProducto.findById(id) ;
          if (productoTraido.isEmpty()){
               return ResponseEntity.notFound().build(); // Solo me da un 404
          }
          return new ResponseEntity<>(productoTraido.get(), HttpStatus.OK);
     }

     @GetMapping(path = {"/productoBase/{id}/seccion"} )
     public ResponseEntity<List<SectorPersonalizacion>> seccionesProducto(Pageable page, @PathVariable Integer id){
          Optional<ProductoBase> productoTraido = repositorioProducto.findById(id);
          if (productoTraido.isEmpty()){
               return ResponseEntity.notFound().build(); // Solo me da un 404
          }
          else {
               List<SectorPersonalizacion> sectores  =  repositorioProducto.findById(id).get().getSectoresPersonalizacionDisponibles();
               return new ResponseEntity<>(sectores, HttpStatus.OK);
          }
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
     public void agregarSeccionProductoBase(@RequestBody @Valid SectorPersonalizacion SectorPersonalizacion,@PathVariable Integer id , BindingResult bindingResult){
          Optional<ProductoBase> producto = repositorioProducto.findById(id);
          if (bindingResult.hasErrors()){
               throw new IllegalStateException("Error en los datos de la seccion del producto cargado");
          }

          else if (producto.isEmpty() ){
               throw new IllegalStateException("El producto referenciado no existe");
          }
          else {
               ProductoBase productoReferenciado = producto.get();
               productoReferenciado.addSectorPersonalizacion(SectorPersonalizacion);
               repositorioProducto.save(productoReferenciado);
          }
     }

     @Transactional
     @DeleteMapping(path = {"/productoBase/{id}"} )
     public ResponseEntity eliminarProductoBase(@PathVariable Integer id){
          Optional<ProductoBase> producto = repositorioProducto.findById(id);
          if (producto.isEmpty()){
               throw new IllegalStateException("Error el producto no existe para eliminarlo");
          }
          else {
                    producto.get().darDeBajaProducto();
                    repositorioProducto.save(producto.get());
                    return new ResponseEntity<>(producto.get(), HttpStatus.OK);
          }
     }
     @Transactional
     @DeleteMapping(path = {"/productoBase/{id}/{seccion}"} )
     public ResponseEntity eliminarSeccionProductoBase(@PathVariable Integer id,@PathVariable Integer seccion){
          Optional<ProductoBase> producto = repositorioProducto.findById(id);
          if (producto.isEmpty()){
               throw new IllegalStateException("Error el producto no existe para eliminarlo");
          }
          else if (producto.get().getSectorPersonalizacion(seccion) == null ){
               throw new IllegalStateException("Error la seccion del producto no existe para eliminarlo");
          }
          else {
               producto.get().deleteSectorPersonalizacion(seccion);
               repositorioProducto.save( producto.get());
               return new ResponseEntity<>(HttpStatus.OK);
          }
     }

}
