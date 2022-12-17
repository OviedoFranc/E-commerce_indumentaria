package ar.utn.ecommerce.models.DTO;

import ar.utn.ecommerce.models.Usuario.MedioPago;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
/*
    public DTOVendedorPost(String nombre, String email, String password, String descripcionVendedor) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.descripcionVendedor =  descripcionVendedor;
    }*/
}


