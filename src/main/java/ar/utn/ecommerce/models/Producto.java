package ar.utn.ecommerce.models;

import lombok.Getter;
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
}
