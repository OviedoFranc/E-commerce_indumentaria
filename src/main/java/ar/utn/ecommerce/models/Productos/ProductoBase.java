package ar.utn.ecommerce.models.Productos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

import static ar.utn.ecommerce.models.Productos.EstadoProducto.CANCELADO;
import static ar.utn.ecommerce.models.Productos.EstadoProducto.DISPONIBLE;


@Entity @Getter @Setter
@Table(name = "productosBase")
public class ProductoBase{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer ID;

    @NotNull
    @Column( name = "nombre")
    private String nombre;

    @NotNull
    @Column( name = "link_foto")
    private String link_foto;

    @Column( name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column( name = "estado")
    private EstadoProducto estadoProducto = DISPONIBLE;

    @NotNull
    @Min(0)
    @Column( name = "precio_base")
    private Double precio_base;

    @NotNull
    @Min(1)
    @Column( name = "tiempo_fabricacion")
    private Integer tiempo_fabricacion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column( name = "categoria")
    private Categoria categoria;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ProductoID" , referencedColumnName = "ID")
    private List<SectorPersonalizacion> sectoresPersonalizacionDisponibles = new ArrayList<SectorPersonalizacion>();;


    public ProductoBase() {

    }

    public ProductoBase(String nombre, String link_foto, String descripcion, Double precio_base, Integer tiempo_fabricacion, Categoria categoria) {
        this.nombre = nombre;
        this.link_foto = link_foto;
        this.descripcion = descripcion;
        this.precio_base = precio_base;
        this.tiempo_fabricacion = tiempo_fabricacion;
        this.categoria = categoria;
    }
    public void darDeBaja (){
        this.estadoProducto = CANCELADO;
    }
    public void addSectorPersonalizacion (SectorPersonalizacion nuevoSector){
        this.sectoresPersonalizacionDisponibles.add(nuevoSector);
    }
}
