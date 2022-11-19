package ar.utn.ecommerce.repository;


import ar.utn.ecommerce.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//@RepositoryRestResource(path = "Producto")
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
