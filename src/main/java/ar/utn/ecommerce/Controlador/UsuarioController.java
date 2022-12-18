package ar.utn.ecommerce.Controlador;


import ar.utn.ecommerce.Repositorio.UsuarioRepository;
import ar.utn.ecommerce.models.Usuario.UsuarioCorriente;
import ar.utn.ecommerce.models.Usuario.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UsuarioController {

    @Autowired
    UsuarioRepository reporitorioUsuario;

    @GetMapping(path = {"/usuario"} )
    public List<UsuarioCorriente> vendedoresPosibles(){
        return reporitorioUsuario.findAll();
    }
    @GetMapping(path = {"/usuario/{Id}"} )
    public ResponseEntity<UsuarioCorriente> vendedoresPosibles(@PathVariable Integer Id){
        Optional<UsuarioCorriente> usuarioTraido =  reporitorioUsuario.findById(Id);
        if (!usuarioTraido.isEmpty())
        {
            return new ResponseEntity<>(usuarioTraido.get(), HttpStatus.OK);
        }
        else throw new IllegalStateException("Error el usuario no existe");
    }

}
