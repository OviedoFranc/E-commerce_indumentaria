package ar.utn.ecommerce.Modelos.Venta;

import ar.utn.ecommerce.Modelos.Productos.ProductoPersonalizadoCompra;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ar.utn.ecommerce.Modelos.Venta.EstadoVenta.*;

@Getter
@Setter
@Entity
@Table(name = "venta")
public class Compra {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer ID;

    @Column(name = "fecha")
    private LocalDate fechaCompra;

    @Column(name = "precioTotal")
    private Double precioTotal;

    @Column(name = "estado")
    private EstadoVenta estado;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "compraID" , referencedColumnName = "ID")
    private List<ProductoPersonalizadoCompra> productos = new ArrayList<>();

    public Compra(List<ProductoPersonalizadoCompra> productos) {
        this.fechaCompra = LocalDate.now();
        this.precioTotal = totalPrecio(productos);
        this.estado = PENDIENTE;
        this.productos = productos;
    }

    public Compra() {

    }

    public Double totalPrecio(List<ProductoPersonalizadoCompra> productos){
        return productos.stream()
                .map(p -> p.getPrecio())
                .collect(Collectors.summingDouble(Double::doubleValue));
    }

    public void ventaConfirmada(){
        this.estado = ABONADO;
    }
    public void ventaCancelada(){
        this.estado = CANCELADO;
    }
}
