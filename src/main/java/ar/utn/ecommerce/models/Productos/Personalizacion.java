package ar.utn.ecommerce.models.Productos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "personalizacion_Productos")
public class Personalizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @Column(name = "nombrePersonalizacion")
    private String nombre;

    @Column(name = "precioPersonalizacion")
    private Integer precio;

    @ManyToOne
    private ProductoPersonalizado producto;

    @Column(name = "personalizacion_sector")
    private String sectorPersonalizacion;

    @Column(name = "personalizacion_tipo")
    private String tipoPersonalizacion;

}
