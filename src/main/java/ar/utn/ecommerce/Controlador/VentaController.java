package ar.utn.ecommerce.Controlador;

import ar.utn.ecommerce.Modelos.Usuario.UsuarioCorriente;
import ar.utn.ecommerce.Modelos.Venta.Compra;
import ar.utn.ecommerce.Repositorio.UsuarioRepository;
import ar.utn.ecommerce.Repositorio.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class VentaController {
    @Autowired
    CompraRepository repositorioVenta;
    @Autowired
    UsuarioRepository repositorioUsuario;

    @GetMapping (path = {"/venta"} )
    public List<Compra> ventasHechas(){
        return repositorioVenta.findAll();
    }
    @GetMapping (path = {"/venta/{usuarioId}"} )
    public ResponseEntity comprasDeUsuario(@PathVariable Integer usuarioId){
        Optional<UsuarioCorriente> usuarioTraido = repositorioUsuario.findById(usuarioId);
        if(usuarioTraido.isEmpty()){
            return new ResponseEntity<>("Error el usuario no existe", HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(  repositorioVenta.findById(usuarioTraido.get().getID()) , HttpStatus.OK);
    }



}
