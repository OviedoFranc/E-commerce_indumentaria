package ar.utn.ecommerce.models.Venta;

import ar.utn.ecommerce.models.Productos.Personalizacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "venta")
public class Venta {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer ID;

    @Column(name = "Comprador")
    private String Nombre;

    @Column(name = "fecha")
    private LocalDate fechaCompra;


}
