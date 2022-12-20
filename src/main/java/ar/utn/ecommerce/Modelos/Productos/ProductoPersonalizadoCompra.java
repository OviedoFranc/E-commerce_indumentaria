package ar.utn.ecommerce.Modelos.Productos;

import ar.utn.ecommerce.Modelos.Venta.Compra;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "productoComprado")
public class ProductoPersonalizadoCompra {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer ID;
    @Column( name = "nombreProducto")
    private String nombre;

    @Column( name = "foto")
    private String foto;

    @Column( name = "descripcion")
    private String descripcion;

    @Column( name = "precio")
    private Double precio;

    @Column( name = "creador")
    private String creador;

    @Column( name = "nombrePersonalizacion")
    private String nombrePersonalizacion;
    @Column( name = "sector")
    private String sectorPersonalizacion;

    @Column( name = "tipoPersonalizacion")
    private String tipoPersonalizacion;

    public ProductoPersonalizadoCompra(ProductoPersonalizado productoPersonalizadoReferenciado, Personalizacion personalizacion) {
        this.nombre = productoPersonalizadoReferenciado.getNombre();
        this.foto = productoPersonalizadoReferenciado.getFoto();
        this.descripcion = productoPersonalizadoReferenciado.getDescripcion();
        this.precio = productoPersonalizadoReferenciado.getPrecio() + personalizacion.getPrecio();
        this.creador = productoPersonalizadoReferenciado.getCreador();
        this.nombrePersonalizacion = personalizacion.getNombre();
        this.sectorPersonalizacion = personalizacion.getSectorPersonalizacion();
        this.tipoPersonalizacion = personalizacion.getTipoPersonalizacion();
    }

    public ProductoPersonalizadoCompra() {

    }


}
