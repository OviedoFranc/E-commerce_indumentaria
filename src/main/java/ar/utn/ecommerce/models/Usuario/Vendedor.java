package ar.utn.ecommerce.models.Usuario;


import ar.utn.ecommerce.models.Productos.ProductoPersonalizado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name ="Vendedor")
public class Vendedor extends Usuario{
    @Transient // ------------------------------------------------------ revisar
    @Enumerated(EnumType.STRING)
    @Column(name = "medioDePago")
    private List<MedioPago> medioDePagoAceptado = new ArrayList<MedioPago>();
    @Transient
    @OneToMany (mappedBy = "creador")
    List<ProductoPersonalizado> productosPersonalizados;

    public Vendedor(String nombre, String email, String password) {
        super(nombre,email,password, TipoCuenta.VENDEDOR);
    }

    public Vendedor() {
        super();
    }

    public void addMedioDePago(MedioPago medioDePago){
        this.medioDePagoAceptado.add(medioDePago);
    }

    public void addProductoPersonalizado(ProductoPersonalizado productoPersonalizado){
        this.productosPersonalizados.add(productoPersonalizado);
        productoPersonalizado.setCreador(this);
    }
}
