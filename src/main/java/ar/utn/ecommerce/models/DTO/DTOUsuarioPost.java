package ar.utn.ecommerce.models.DTO;

import ar.utn.ecommerce.models.Usuario.MedioPago;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class DTOUsuarioPost {

    String nombre;
    String email;
    String password;

    public DTOUsuarioPost(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

}





