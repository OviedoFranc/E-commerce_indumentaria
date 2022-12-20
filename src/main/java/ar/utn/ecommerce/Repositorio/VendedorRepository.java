package ar.utn.ecommerce.Repositorio;

import ar.utn.ecommerce.Modelos.Usuario.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

}
