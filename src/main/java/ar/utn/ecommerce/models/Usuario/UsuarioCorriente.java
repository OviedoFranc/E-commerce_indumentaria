package ar.utn.ecommerce.models.Usuario;

import ar.utn.ecommerce.models.Carrito.CarritoCompra;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name ="UsuarioCorriente")
public class UsuarioCorriente extends Usuario{

    @Transient
    @ManyToMany
    List<CarritoCompra> productosEnCarrito;

    public UsuarioCorriente(String nombre, String email, String password) {
        super(nombre,email,password, TipoCuenta.CORRRIENTE);
    }

    public UsuarioCorriente() {
        super();
    }
    /*
    public void addProductoAlCarrito(ProductoPersonalizado productoPersonalizado){
        this.productosEnCarrito.add(productoPersonalizado);
        productoPersonalizado.setCreador(this);
    }

     */
}