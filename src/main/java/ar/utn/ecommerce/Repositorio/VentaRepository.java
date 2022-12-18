package ar.utn.ecommerce.Repositorio;

import ar.utn.ecommerce.models.Venta.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
