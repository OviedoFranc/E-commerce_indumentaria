package ar.utn.ecommerce.Controlador;
import ar.utn.ecommerce.Modelos.DTO.*;
import ar.utn.ecommerce.Modelos.Usuario.*;
import ar.utn.ecommerce.Repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    UsuarioRepository reporitorioUsuario;
    @PostMapping("/login")
    public Optional<DTOUsuarioValido> login(@RequestBody DTOLogin usuario) {
        Optional<UsuarioCorriente> usuarioBuscado =  reporitorioUsuario.findByEmail( usuario.getEmail() );

        if(usuarioBuscado.isEmpty()) return null;
        else {
             if( usuarioBuscado.get().getPassword().equals(usuario.getPassword()) ){
                 DTOUsuarioValido usuarioValido = new DTOUsuarioValido(usuarioBuscado.get().getID(), usuarioBuscado.get().getNombre() ) ;
                 return Optional.of(usuarioValido);
             }
             else {
                 return null;
             }
        }
    }

}