package ar.utn.ecommerce.Controlador;

import ar.utn.ecommerce.Repositorio.ProductoPersonalizadoRepository;
import ar.utn.ecommerce.Modelos.DTO.DTOVendedorPost;
import ar.utn.ecommerce.Modelos.Productos.ProductoPersonalizado;
import ar.utn.ecommerce.Modelos.Usuario.Vendedor;
import ar.utn.ecommerce.Repositorio.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


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

        @GetMapping(path = {"/{vendedor}/productoPersonalizado"} )
        public List<ProductoPersonalizado> productosPersonalizadosDeVendedor(@PathVariable Integer vendedorId){

        Optional<Vendedor> vendedorTraido = repositorioVendedor.findById(vendedorId);
        return repositorioProductoPersonalizado.findByCreador(vendedorTraido.get().getNombre());

        }

        @GetMapping(path = {"/vendedor/{vendedorId}"} )
        public ResponseEntity<Vendedor> obtenerVendedor(@PathVariable Integer vendedorId){
            Optional<Vendedor> vendedorTraido = repositorioVendedor.findById(vendedorId) ;
            if (vendedorTraido.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(vendedorTraido.get(), HttpStatus.OK);
        }

        @PostMapping(path = {"/vendedor"} )
        public Vendedor agregarVendedor(@RequestBody @Valid DTOVendedorPost vendedor){

            Vendedor vendedorNuevo = new Vendedor(vendedor.getNombre(), vendedor.getEmail(), vendedor.getPassword(), vendedor.getLink_fotoUsuario(),vendedor.getDescripcion(),vendedor.getMedioDePagoAceptado());
            return repositorioVendedor.save(vendedorNuevo);

        }

        @DeleteMapping(path = {"/vendedor/{vendedorId}"} )
        public void eliminarVendedorYproductosPersonalizados(@PathVariable Integer vendedorId){
                Optional<Vendedor> vendedorTraido = repositorioVendedor.findById(vendedorId);
                if(!vendedorTraido.isEmpty()) {
                    vendedorTraido.get().darDeBaja();
                    repositorioVendedor.save(vendedorTraido.get());
                }
                else throw new IllegalStateException("Error vendedor no existe");
            }

}
