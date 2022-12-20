package ar.utn.ecommerce.Controlador;


import ar.utn.ecommerce.Modelos.DTO.DTOProdPersoCompra;
import ar.utn.ecommerce.Modelos.Productos.ProductoPersonalizado;
import ar.utn.ecommerce.Modelos.Productos.ProductoPersonalizadoCompra;
import ar.utn.ecommerce.Modelos.Usuario.Vendedor;
import ar.utn.ecommerce.Modelos.Venta.Compra;
import ar.utn.ecommerce.Repositorio.*;
import ar.utn.ecommerce.Modelos.DTO.DTOUsuarioPost;
import ar.utn.ecommerce.Modelos.Usuario.UsuarioCorriente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ar.utn.ecommerce.Modelos.Venta.EstadoVenta.*;

@RestController
@CrossOrigin
public class UsuarioController {
    @Autowired
    ProductoPersonalizadoRepository repositorioProductoPersonalizado;
    @Autowired
    PersonalizacionesRepository repositorioPersonalizacion;
    @Autowired
    VendedorRepository repositorioVendedor;
    @Autowired
    UsuarioRepository reporitorioUsuario;
    @Autowired
    CompraRepository compraRepository;

    @Autowired
    PersonalizacionesRepository personalizacionesRepository;

    @Autowired
     ProductoPersonalizadoRepository productoPersonalizadoRepository;

    @GetMapping(path = {"/usuario"} )
    public List<UsuarioCorriente> usuariosCorrientes(){
        return reporitorioUsuario.findAll();
    }
    @GetMapping(path = {"/usuario/{usuarioId}"} )
    public ResponseEntity<UsuarioCorriente> usuarioCorrienteParticular(@PathVariable Integer usuarioId){
        Optional<UsuarioCorriente> usuarioTraido =  reporitorioUsuario.findById(usuarioId);
        if (!usuarioTraido.isEmpty())
        {
            return new ResponseEntity<>(usuarioTraido.get(), HttpStatus.OK);
        }
        else throw new IllegalStateException("Error el usuario no existe");
    }

    @GetMapping(path = {"/usuario/{usuarioId}/compra"} )
    public ResponseEntity usuarioCorrienteCompras(@PathVariable Integer usuarioId){
        Optional<UsuarioCorriente> usuarioTraido =  reporitorioUsuario.findById(usuarioId);
        if (!usuarioTraido.isEmpty())
        {
            return new ResponseEntity<>( usuarioTraido.get().getComprasRealizadas() , HttpStatus.OK);
        }
        else throw new IllegalStateException("Error el usuario no existe");
    }

        @PostMapping(path = {"/usuario"} )
        public ResponseEntity<UsuarioCorriente> crearUsuarioCorriente(@Valid @RequestBody DTOUsuarioPost usuarioPost, BindingResult bindingResult){
            if (bindingResult.hasErrors()){
                 throw new IllegalStateException("Error los datos del usuario estan en un mal formato");
            }
            else {
                UsuarioCorriente usuarioNuevo = new UsuarioCorriente(usuarioPost.getNombre(), usuarioPost.getEmail(), usuarioPost.getPassword());
                reporitorioUsuario.save(usuarioNuevo);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }

        @PostMapping(path = {"/usuario/{usuarioId}/compra"} )
        public ResponseEntity agregarCompra(@PathVariable Integer usuarioId,
                                            @Valid @RequestBody List<DTOProdPersoCompra> productosDelCarrito,
                                            BindingResult bindingResult){
            Optional<UsuarioCorriente> usuarioTraido =  reporitorioUsuario.findById(usuarioId);
            if ( usuarioTraido.isEmpty() ){
                return new ResponseEntity<>("Error el usuario no existe", HttpStatus.NOT_FOUND);
            }
            else if(bindingResult.hasErrors() )
            {
                return new ResponseEntity<>("Error en los productos cargados", HttpStatus.BAD_REQUEST);
            }
            else{
                List<ProductoPersonalizadoCompra> productosComprados = new ArrayList<>();
                for (DTOProdPersoCompra dtoProducto : productosDelCarrito){
                    Optional<ProductoPersonalizado> producto = repositorioProductoPersonalizado.findById(dtoProducto.getProductoPersonalizadoRefID());

                    if (producto.isEmpty()) {
                        return new ResponseEntity<>("Error: el producto no existe", HttpStatus.CONFLICT);
                    }
                    Optional<Vendedor> vendedor = repositorioVendedor.findById(producto.get().getCreadorID());
                    if (vendedor.isEmpty()) {
                        return new ResponseEntity<>("Error: el vendedor no existe", HttpStatus.CONFLICT);
                    }
                    productosComprados.add(new ProductoPersonalizadoCompra(producto.get(), producto.get().damePersonalizacion( dtoProducto.getPersonalizacionRefID() ) ) );
                }
                Compra compraNueva = new Compra(productosComprados);
                usuarioTraido.get().addCompra(compraNueva);
                reporitorioUsuario.save(usuarioTraido.get());
                return new ResponseEntity<>(usuarioTraido.get(),HttpStatus.CREATED);
            }
        }
/*
        @PostMapping(path = {"/usuario/{usuarioId}/{compraId}/confirmarCompra"} )
        public ResponseEntity confirmarCompra(@PathVariable Integer usuarioId,
                                              @PathVariable Integer compraId){
        Optional<UsuarioCorriente> usuarioTraido =  reporitorioUsuario.findById(usuarioId);

        if ( usuarioTraido.isEmpty() ){
            return new ResponseEntity<>("Error el usuario no existe", HttpStatus.NOT_FOUND);
        }
        else if( ventaRepository.findById(compraId).isEmpty() )
        {
            return new ResponseEntity<>("Error la compra no existe", HttpStatus.NOT_FOUND);
        }
        else{
            Venta compra =  ventaRepository.findById(compraId).get();
            if ( compra.getComprador().getID() == usuarioTraido.get().getID()  ){
                compra.ventaConfirmada();
                ventaRepository.save(compra);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            else return new ResponseEntity<>("Error este usuario no posee esa compra", HttpStatus.NOT_FOUND);
            }
       }

    @PostMapping(path = {"/usuario/{usuarioId}/{compraId}/cancelarCompra"} )
    public ResponseEntity cancelarCompra(@PathVariable Integer usuarioId,
                                          @PathVariable Integer compraId){
        Optional<UsuarioCorriente> usuarioTraido =  reporitorioUsuario.findById(usuarioId);

        if ( usuarioTraido.isEmpty() ){
            return new ResponseEntity<>("Error el usuario no existe", HttpStatus.NOT_FOUND);
        }
        else if( ventaRepository.findById(compraId).isEmpty() )
        {
            return new ResponseEntity<>("Error la compra no existe", HttpStatus.NOT_FOUND);
        }
        else{
            Venta compra =  ventaRepository.findById(compraId).get();
            if ( compra.getComprador().getID() ==  usuarioTraido.get().getID() ){
                compra.ventaCancelada();
                ventaRepository.save(compra);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            else return new ResponseEntity<>("Error este usuario no posee esa compra", HttpStatus.NOT_FOUND);
        }
    }
*/
}
