package ar.utn.ecommerce.models.Productos;

import ar.utn.ecommerce.models.Usuario.Vendedor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static ar.utn.ecommerce.models.Productos.EstadoProducto.*;


@Entity
@Getter @Setter @Table(name = "productosPersonalizados")
public class ProductoPersonalizado {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer ID;

    @Column( name = "nombre")
    private String nombre;

    @Column( name = "foto")
    private String foto;

    @ManyToOne
    @JoinColumn(name = "productoBaseReferenciado", referencedColumnName = "ID")
    private ProductoBase productoBaseReferenciado;

    @Column( name = "descripcion")
    private String descripcion;

    @Column( name = "precio")
    private Double precio;

    @Enumerated(EnumType.STRING)
    @Column( name = "estado")
    private EstadoProducto estadoProducto = DISPONIBLE;  // revisar que cuando de de baja un prodBase este se de de baja solo.

    @Column( name = "tiempo_fabricacion")
    private Integer tiempo_fabricacion;
    /*
    @Transient
    @Column(name = "vendedor")
    private String vendedor;
    */

    @Enumerated(EnumType.STRING)
    @Column( name = "categoria")
    private Categoria categoria;
    @Transient
    @OneToMany(mappedBy = "producto",cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
    private List<Personalizacion> personalizaciones = new ArrayList<>();


    public ProductoPersonalizado(String nombre, ProductoBase productoBaseReferenciado /*,Vendedor vendedor*/) {
        this.nombre = nombre;
        this.productoBaseReferenciado = productoBaseReferenciado;
        this.foto = productoBaseReferenciado.getFoto();
        this.descripcion = productoBaseReferenciado.getDescripcion();
        this.precio =  productoBaseReferenciado.getPrecio_base();
        this.categoria = productoBaseReferenciado.getCategoria();
        this.tiempo_fabricacion = productoBaseReferenciado.getTiempo_fabricacion();
        //this.vendedor = vendedor.getNombre();
    }


    public ProductoPersonalizado() {

    }

    public void addPersonalizacion(Personalizacion personalizacion){
        this.personalizaciones.add(personalizacion);
        personalizacion.setProducto(this);
    }

}
