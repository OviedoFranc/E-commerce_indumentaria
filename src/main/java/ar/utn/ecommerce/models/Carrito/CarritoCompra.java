package ar.utn.ecommerce.models.Carrito;

import ar.utn.ecommerce.models.Productos.ProductoPersonalizado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name ="carritoDeCompra")
public class CarritoCompra {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer ID;

    @Column(name = "usuarioID")
    private String usuarioID;

    @Transient
    @OneToMany
    @Column(name = "usuarioID")
    List<ProductoPersonalizado> productosEnCarrito;

}
