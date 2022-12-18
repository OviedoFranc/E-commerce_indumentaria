package ar.utn.ecommerce.models.Productos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Column( name = "foto")
    private String foto;

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
    private List<SectorPersonalizacion> sectoresPersonalizacionDisponibles = new ArrayList<>();


    public ProductoBase() {

    }

    public ProductoBase(String nombre, String foto, String descripcion, Double precio_base, Integer tiempo_fabricacion, Categoria categoria) {
        this.nombre = nombre;
        this.foto = foto;
        this.descripcion = descripcion;
        this.precio_base = precio_base;
        this.tiempo_fabricacion = tiempo_fabricacion;
        this.categoria = categoria;
    }
    public void darDeBajaProducto(){
        this.estadoProducto = CANCELADO;
    }

    public void addSectorPersonalizacion (SectorPersonalizacion nuevoSector){
        this.sectoresPersonalizacionDisponibles.add(nuevoSector);
    }

    public Boolean poseeSectorPersonalizacion (String sector){
        return this.sectoresPersonalizacionDisponibles.stream()
                    .anyMatch(p -> p.getSectorPersonalizacion() == sector);
    }

    public SectorPersonalizacion getSectorPersonalizacion (Integer sectorID){
        SectorPersonalizacion sector = this.sectoresPersonalizacionDisponibles.stream()
                .filter(p -> p.getSectorID() == sectorID)
                .findFirst()
                .orElse(null);
        return sector;
    }

    public void deleteSectorPersonalizacion(Integer sectorID) {
        List<SectorPersonalizacion> listaFiltrada = this.sectoresPersonalizacionDisponibles.stream()
                .filter(p -> p.getSectorID() != sectorID)
                .collect(Collectors.toList());
        this.setSectoresPersonalizacionDisponibles(listaFiltrada);
    }
}
