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

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<MedioPago> medioDePagoAceptado = new ArrayList<>();

    @Column(name = "descripcionVendedor")
    private String descripcion;

    @Column( name = "link_fotoUsuario")
    private String link_fotoUsuario = "";
    /*
    @Transient
    @OneToMany (mappedBy = "creador")
    List<ProductoPersonalizado> productosPersonalizados;
    */

                                                                    //TODO AGREGAR MEDIOS DE PAGO AL CONSTRUCTOR
    public Vendedor(String nombre, String email, String password , String link_Foto,String descripcion,List<MedioPago> medioDePago) {
        super(nombre,email,password, TipoCuenta.VENDEDOR);
        this.link_fotoUsuario = link_Foto;
        this.descripcion = descripcion;
        this.medioDePagoAceptado = medioDePago;
    }

    public Vendedor() {

    }


    public void addMedioDePago(MedioPago medioDePago){
        this.medioDePagoAceptado.add(medioDePago);
    }
    /*
    public void addProductoPersonalizado(ProductoPersonalizado productoPersonalizado){
        this.productosPersonalizados.add(productoPersonalizado);
        productoPersonalizado.setCreador(this);
    }*/
}
