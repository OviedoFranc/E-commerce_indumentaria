package ar.utn.ecommerce.models.Productos;

import ar.utn.ecommerce.models.Usuario.Vendedor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Column( name = "creador")
    private String creador;


    @Enumerated(EnumType.STRING)
    @Column( name = "categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
    private List<Personalizacion> personalizaciones = new ArrayList<>();


    public ProductoPersonalizado(String nombre, ProductoBase productoBaseReferenciado ,Vendedor vendedor) {
        this.nombre = nombre;
        this.productoBaseReferenciado = productoBaseReferenciado;
        this.foto = productoBaseReferenciado.getFoto();
        this.descripcion = productoBaseReferenciado.getDescripcion();
        this.precio =  productoBaseReferenciado.getPrecio_base();
        this.categoria = productoBaseReferenciado.getCategoria();
        this.tiempo_fabricacion = productoBaseReferenciado.getTiempo_fabricacion();
        vendedor.addProductoPersonalizado(this);
    }


    public ProductoPersonalizado() {

    }

    public void darDeBajaProducto(){
        this.estadoProducto = CANCELADO;
    }

    public void pausarProducto(){
        this.estadoProducto = PAUSADO;
    }

    public void addPersonalizacion(Personalizacion personalizacion){
        this.personalizaciones.add(personalizacion);
        personalizacion.setProducto(this);
    }

    public void deletePersonalizacion(Integer personalizacionId){
        this.setPersonalizaciones( this.personalizaciones.stream()
                .filter(i -> i.getId() != personalizacionId)
                .collect(Collectors.toList()));
    }

}
