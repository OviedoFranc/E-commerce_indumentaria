package ar.utn.ecommerce.Modelos.Productos;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "personalizacion_Productos")
public class Personalizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;

    @Column(name = "nombrePersonalizacion")
    private String nombre;

    @Column(name = "precioPersonalizacion")
    private Double precio;


    @Column(name = "personalizacion_sector")
    private String sectorPersonalizacion;

    @Column(name = "personalizacion_tipo")
    private String tipoPersonalizacion;

    public Personalizacion(String nombre, Double precio, String sectorPersonalizacion, String tipoPersonalizacion) {
        this.nombre = nombre;
        this.precio = precio;

        this.sectorPersonalizacion = sectorPersonalizacion;
        this.tipoPersonalizacion = tipoPersonalizacion;
    }

    public Personalizacion() {
    }

    @Override
    public String toString() {
        return "Personalizacion{" +
                "Id=" + ID +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", sectorPersonalizacion='" + sectorPersonalizacion + '\'' +
                ", tipoPersonalizacion='" + tipoPersonalizacion + '\'' +
                '}';
    }
}
