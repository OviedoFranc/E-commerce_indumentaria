package ar.utn.ecommerce.models.Productos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static ar.utn.ecommerce.models.Productos.EstadoProducto.DISPONIBLE;

@Entity
@Getter
@Setter
@Table(name = "productoComprado")
public class ProducoPersonalizadoCompra {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer ID;

    @Column( name = "nombre")
    private String nombre;

    @Column( name = "foto")
    private String foto;

    @ManyToOne
    @JoinColumn(name = "productoPersonalizadoReferenciado", referencedColumnName = "ID")
    private ProductoPersonalizado productoPersonalizadoReferenciado;

    @Column( name = "descripcion")
    private String descripcion;

    @Column( name = "precio")
    private Double precio;

    @Column( name = "creador")
    private String creador;

    @Column( name = "sector")
    private String sectorPersonalizacion;

    @Column( name = "tipoPersonalizacion")
    private String tipoPersonalizacion;

    public ProducoPersonalizadoCompra(String nombre, String foto, ProductoPersonalizado productoPersonalizadoReferenciado, String descripcion, Double precio, String creador, String sectorPersonalizacion, String tipoPersonalizacion) {
        this.nombre = nombre;
        this.foto = foto;
        this.productoPersonalizadoReferenciado = productoPersonalizadoReferenciado;
        this.descripcion = descripcion;
        this.precio = precio;
        this.creador = creador;
        this.sectorPersonalizacion = sectorPersonalizacion;
        this.tipoPersonalizacion = tipoPersonalizacion;
    }
}
