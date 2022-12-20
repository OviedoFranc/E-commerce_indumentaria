package ar.utn.ecommerce.Modelos.DTO;

import ar.utn.ecommerce.Modelos.Usuario.MedioPago;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DTOVendedorPost {

    String nombre;
    String email;
    String password;
    String link_fotoUsuario;
    String descripcion;
    List<MedioPago> medioDePagoAceptado;

    public DTOVendedorPost(String nombre, String email, String password,String link_fotoUsuario, String descripcion, List<MedioPago> mediosDePago) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.link_fotoUsuario = link_fotoUsuario;
        this.descripcion =  descripcion;
        this.medioDePagoAceptado = mediosDePago;
    }

}


