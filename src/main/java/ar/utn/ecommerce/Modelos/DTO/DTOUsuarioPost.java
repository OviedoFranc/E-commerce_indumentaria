package ar.utn.ecommerce.Modelos.DTO;

import lombok.Getter;
import lombok.Setter;

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





