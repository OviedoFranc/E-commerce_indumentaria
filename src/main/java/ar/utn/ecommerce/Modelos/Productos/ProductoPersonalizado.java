package ar.utn.ecommerce.Modelos.Productos;

import ar.utn.ecommerce.Modelos.Usuario.Vendedor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ar.utn.ecommerce.Modelos.Productos.EstadoProducto.*;


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
    private EstadoProducto estadoProducto = DISPONIBLE;

    @Column( name = "tiempo_fabricacion")
    private Integer tiempo_fabricacion;

    @Column( name = "creador")
    private String creador;

    @Column( name = "creadorID")
    private Integer creadorID;


    @Enumerated(EnumType.STRING)
    @Column( name = "categoria")
    private Categoria categoria;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "productoPersonalizadoID" , referencedColumnName = "ID")
    private List<Personalizacion> personalizaciones = new ArrayList<>();


    public ProductoPersonalizado(String nombre, ProductoBase productoBaseReferenciado ,Vendedor vendedor) {
        this.nombre = nombre;
        this.creadorID = vendedor.getID();
        this.productoBaseReferenciado = productoBaseReferenciado;
        this.foto = productoBaseReferenciado.getFoto();
        this.descripcion = productoBaseReferenciado.getDescripcion();
        this.precio =  productoBaseReferenciado.getPrecio_base();
        this.categoria = productoBaseReferenciado.getCategoria();
        this.tiempo_fabricacion = productoBaseReferenciado.getTiempo_fabricacion();
        vendedor.addProductoPersonalizado(this);
    }

    public Boolean permitirPersonalizacionSeccion(String Seccion){

        if(this.productoBaseReferenciado.poseeSectorPersonalizacion(Seccion)) {
            System.out.println("El producto personalizacion puede usar esta seccion personalizacion");
            return true;
        }
        else return false;

    }
    public Boolean permitirPersonalizacionTipo(String tipoPersonalizacion, String Seccion){

        List<SectorPersonalizacion> sectoresProdBase = this.productoBaseReferenciado.getSectoresPersonalizacionDisponibles();

        boolean tipoIsOk = sectoresProdBase.stream().filter(s -> s.equals(Seccion) ).findFirst().get().poseeTipoPersonalizacion(tipoPersonalizacion);

        if(tipoIsOk){
            System.out.println("El producto personalizacion puede usar esta seccion con este tipo personalizacion");
            return true;
        }
        else return false;

    }
    public Boolean permitirPersonalizacion(Personalizacion personalizacion){


        if(permitirPersonalizacionSeccion(personalizacion.getSectorPersonalizacion()) && permitirPersonalizacionTipo(personalizacion.getTipoPersonalizacion(), personalizacion.getSectorPersonalizacion()) ) return true;
        else return false;

    }

    public Personalizacion damePersonalizacion(Integer IdPersonalizacion){
        return this.personalizaciones.stream().filter(p ->  IdPersonalizacion == p.getID()  ).findFirst().get();
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
    }

    public void deletePersonalizacion(Integer personalizacionId){
        this.setPersonalizaciones( this.personalizaciones.stream()
                .filter(i ->  i.getID() != personalizacionId )
                .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return "ProductoPersonalizado{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", foto='" + foto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", estadoProducto=" + estadoProducto +
                ", tiempo_fabricacion=" + tiempo_fabricacion +
                ", creador='" + creador + '\'' +
                ", categoria=" + categoria +
                ", personalizaciones=" + personalizaciones.toString() +
                '}';
    }
}
