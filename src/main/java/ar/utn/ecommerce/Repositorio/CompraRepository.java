package ar.utn.ecommerce.Repositorio;

import ar.utn.ecommerce.Modelos.Venta.EstadoVenta;
import ar.utn.ecommerce.Modelos.Venta.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {

}
