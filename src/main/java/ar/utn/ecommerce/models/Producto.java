package ar.utn.ecommerce.models;

import javax.validation.constraints.NotNull;
import lombok.Getter;
<<<<<<< Updated upstream
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

public class Producto {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private Double precio_base;
    @Getter @Setter
    private Integer tiempo_fabricacion;
    @Getter @Setter
    private String creador;
    @Getter @Setter
    private ArrayList<Personalizacion> personalizacionesDisponibles = new ArrayList<Personalizacion> ();

    public void addPersonalizacionesDisponibles(Personalizacion personalizacion) {
        this.personalizacionesDisponibles.add(personalizacion);
    }
=======
import javax.validation.constraints.Min;

import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity @Getter @NoArgsConstructor
public class Producto {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long Id;
    @NotNull
    private String nombre;
    @NotNull
    private String link_foto;

    private String descripcion;
    @NotNull
    @Min(0)
    private Double precio_base;
    @NotNull
    @Min(1)
    private Integer tiempo_fabricacion;
    @NotNull
    private String creador;
    @NotNull
    private String estado;
    @NotNull
    private String categoria;

    public Producto(String nombre, String link_foto, String descripcion, Double precio_base, Integer tiempo_fabricacion, String creador, String estado, String categoria) {
        this.nombre = nombre;
        this.link_foto = link_foto;
        this.descripcion = descripcion;
        this.precio_base = precio_base;
        this.tiempo_fabricacion = tiempo_fabricacion;
        this.creador = creador;
        this.estado = estado;
        this.categoria = categoria;
    }

>>>>>>> Stashed changes
}
