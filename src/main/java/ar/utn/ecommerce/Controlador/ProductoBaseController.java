package ar.utn.ecommerce.Controlador;
import ar.utn.ecommerce.models.Productos.ProductoBase;
import ar.utn.ecommerce.models.Productos.SectorPersonalizacion;
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

import static ar.utn.ecommerce.models.Productos.EstadoProducto.*;


@RestController
@CrossOrigin
public class ProductoBaseController {

     @Autowired
     ProductoBaseRepository repositorioProducto;

     @GetMapping(path = {"/productoBase"} )
     public ProductoBase productoBaseDisponibles() {
          return repositorioProducto.findByEstadoProducto(DISPONIBLE);
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
                                                                                                    //TODO: PROBAR
     @Transactional
     @PostMapping(path = {"/productoBase/{id}/seccion"} )
     public void agregarSeccion(@RequestBody @Valid SectorPersonalizacion SectorPersonalizacion,@PathVariable Integer id , BindingResult bindingResult){
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
     @PostMapping(path = {"/productoBase/{id}/{seccionId}/tipoPersonalizacion"} )// Comprobar si el ID existe  // control de errores si esta vacio
     public void agregarTipoPersonalizacion(@RequestBody @Valid String tipoPersonalizacion, @PathVariable Integer id, @PathVariable Integer seccionId,BindingResult bindingResult){
          Optional<ProductoBase> producto = repositorioProducto.findById(id);
          if (bindingResult.hasErrors()){
               throw new IllegalStateException("Error en los datos del tipo de personalizacion cargado");
          }

          else if (producto.isEmpty() ){
               throw new IllegalStateException("El producto referenciado no existe");
          }
          else if(  producto.get().getSectorPersonalizacion(seccionId).equals(null) ){
               throw new IllegalStateException("El producto referenciado no posee esa seccion");
          }
          else {
               SectorPersonalizacion sector = producto.get().getSectorPersonalizacion(seccionId);
               sector.addposiblesTipoPersonalizacion(tipoPersonalizacion);
               repositorioProducto.save(producto.get());   //TODO: REVISAR SI GUARDO EL PRODUCTO O GUARDO EL SECTOR
          }
     }
     // TODO: LOS DELETE
     @Transactional
     @DeleteMapping(path = {"/productoBase"} )
     public ResponseEntity eliminarProducto(@RequestBody @Valid ProductoBase productoEliminar, BindingResult bindingResult){
          if (bindingResult.hasErrors()){
               throw new IllegalStateException("Error en los datos del tipo de personalizacion cargado");
          }
          else {
               Optional<ProductoBase> producto = repositorioProducto.findById(productoEliminar.getID());
               if (producto.isEmpty()){
                    return ResponseEntity.notFound().build(); // Solo me da un 404
               }
               else {
                    producto.get().setEstadoProducto(CANCELADO);
                    return new ResponseEntity<>(producto.get(), HttpStatus.OK);
               }
          }
     }

}
