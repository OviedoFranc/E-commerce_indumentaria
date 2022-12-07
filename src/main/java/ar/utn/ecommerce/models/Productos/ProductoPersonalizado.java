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

    @Column( name = "link_foto")
    private String link_foto;

    @ManyToOne
    @JoinColumn(name = "productoBaseReferenciado", referencedColumnName = "ID")
    private ProductoBase productoBaseReferenciado;

    private Integer productoBaseReferenciadoID;

    @Column( name = "descripcion")
    private String descripcion;

    @Column( name = "precio")
    private Double precio;

    @Enumerated(EnumType.STRING)
    @Column( name = "estado")
    private EstadoProducto estadoProducto = DISPONIBLE;  // revisar que cuando de de baja un prodBase este se de de baja solo.

    @Column( name = "tiempo_fabricacion")
    private Integer tiempo_fabricacion;
    @Transient
    @ManyToOne
    @JoinColumn(name = "creador" , referencedColumnName = "ID")
    private Vendedor creador;
    @Enumerated(EnumType.STRING)
    @Column( name = "categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "productoPersonalizado", cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
    private List<Personalizacion> personalizacion = new ArrayList<>();


    public ProductoPersonalizado(String nombre, ProductoBase productoBaseReferenciado /*,Vendedor creador*/) {
        this.nombre = nombre;
        this.productoBaseReferenciado = productoBaseReferenciado;
        this.link_foto = productoBaseReferenciado.getLink_foto();
        this.descripcion = productoBaseReferenciado.getDescripcion();
        this.precio =  productoBaseReferenciado.getPrecio_base();
        this.categoria = productoBaseReferenciado.getCategoria();
       // this.creador = creador;
    }


    public ProductoPersonalizado() {

    }

    public void addPersonalizacion(Personalizacion personalizacion){
        this.personalizacion.add(personalizacion);
        personalizacion.setProductoPersonalizado(this);
    }




}
