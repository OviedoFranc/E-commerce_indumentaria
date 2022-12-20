package ar.utn.ecommerce.Modelos.Usuario;


import ar.utn.ecommerce.Modelos.Productos.ProductoPersonalizado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static ar.utn.ecommerce.Modelos.Usuario.EstadoCuenta.*;

@Entity
@Getter @Setter
@Table(name ="Vendedor")
public class Vendedor extends Usuario{
    @Enumerated(EnumType.STRING)
    @Column(name = "estadoCuenta")
    private EstadoCuenta estado;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<MedioPago> medioDePagoAceptado = new ArrayList<>();

    @Column(name = "descripcionVendedor")
    private String descripcion;

    @Column( name = "link_fotoUsuario")
    private String link_fotoUsuario = "";

    @OneToMany (mappedBy = "creador")
    List<ProductoPersonalizado> productosPersonalizados = new ArrayList<>();



    public Vendedor(String nombre, String email, String password , String link_Foto,String descripcion,List<MedioPago> medioDePago) {
        super(nombre,email,password, TipoCuenta.VENDEDOR);
        this.link_fotoUsuario = link_Foto;
        this.descripcion = descripcion;
        this.medioDePagoAceptado = medioDePago;
        this.estado = DISPONIBLE;
    }

    public Vendedor() {

    }
    public void darDeBaja(){
        this.estado = NODISPONIBLE;
        productosPersonalizados.forEach(producto -> producto.darDeBajaProducto());
    }

    public void addMedioDePago(MedioPago medioDePago){
        this.medioDePagoAceptado.add(medioDePago);
    }
    /*
    public void deleteMedioDePago(MedioPago medioDePago){
        this.medioDePagoAceptado.add(medioDePago);
    }*/
    public void addProductoPersonalizado(ProductoPersonalizado productoPersonalizado){
        this.productosPersonalizados.add(productoPersonalizado);
        productoPersonalizado.setCreador(this.getNombre());
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "Id= "+ this.getID()+
                " estado=" + estado +
                ", medioDePagoAceptado=" + medioDePagoAceptado +
                ", descripcion='" + descripcion + '\'' +
                ", link_fotoUsuario='" + link_fotoUsuario + '\'' +
                ", productosPersonalizados=" + productosPersonalizados +
                '}';
    }
}
