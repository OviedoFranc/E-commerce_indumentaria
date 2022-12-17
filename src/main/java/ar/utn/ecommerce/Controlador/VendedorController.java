package ar.utn.ecommerce.Controlador;

import ar.utn.ecommerce.Repositorio.ProductoPersonalizadoRepository;
import ar.utn.ecommerce.models.DTO.DTOVendedorPost;
import ar.utn.ecommerce.models.Productos.ProductoPersonalizado;
import ar.utn.ecommerce.models.Usuario.Vendedor;
import ar.utn.ecommerce.Repositorio.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static ar.utn.ecommerce.models.Productos.EstadoProducto.DISPONIBLE;

@RestController
@CrossOrigin
public class VendedorController {

        @Autowired
        VendedorRepository repositorioVendedor;
        @Autowired
        ProductoPersonalizadoRepository repositorioProductoPersonalizado;

        @GetMapping(path = {"/vendedor"} )
        public List<Vendedor> vendedoresPosibles(){
            return repositorioVendedor.findAll();
        }

        @GetMapping(path = {"/vendedor/{Id}"} )
        public ResponseEntity<Vendedor> vendedor(@PathVariable Integer Id){
            Optional<Vendedor> vendedorTraido = repositorioVendedor.findById(Id) ;
            if (vendedorTraido.isEmpty()){
                return ResponseEntity.notFound().build(); // Solo me da un 404
            }
            return new ResponseEntity<>(vendedorTraido.get(), HttpStatus.OK);
        }
/*
        @GetMapping(path = {"/vendedor/{Id}/productoPersonalizado"} )
        public ResponseEntity< List<ProductoPersonalizado> > productosDelVendedor(@PathVariable Integer Id){
        Optional<Vendedor> vendedorTraido = repositorioVendedor.findById(Id) ;
        if (vendedorTraido.isEmpty()){
            return ResponseEntity.notFound().build(); // Solo me da un 404
        }
        else {
            List<ProductoPersonalizado> productosDelVendedor =  repositorioProductoPersonalizado.findByCreador(vendedorTraido.get());
            return new ResponseEntity<>(repositorioProductoPersonalizado.findAllByVendedorId(Id), HttpStatus.OK);
        }
    }
*/
        @PostMapping(path = {"/vendedor"} )
        public Vendedor agregarVendedor(@RequestBody @Valid DTOVendedorPost vendedor){
            if(vendedor.getLink_fotoUsuario().equals(""))
            {
                Vendedor vendedorNuevoSinFoto = new Vendedor(vendedor.getNombre(), vendedor.getEmail(), vendedor.getPassword(), "fotoBasicaFalsa.jpg", vendedor.getDescripcion(),vendedor.getMedioDePagoAceptado());
                return repositorioVendedor.save(vendedorNuevoSinFoto);
            }
            else {
                Vendedor vendedorNuevo = new Vendedor(vendedor.getNombre(), vendedor.getEmail(), vendedor.getPassword(), vendedor.getLink_fotoUsuario(),vendedor.getDescripcion(),vendedor.getMedioDePagoAceptado());
                return repositorioVendedor.save(vendedorNuevo);
            }
        }

}
