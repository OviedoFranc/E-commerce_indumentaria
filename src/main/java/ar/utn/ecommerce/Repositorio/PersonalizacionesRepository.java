package ar.utn.ecommerce.Repositorio;

import ar.utn.ecommerce.Modelos.Productos.Personalizacion;
import ar.utn.ecommerce.Modelos.Productos.ProductoBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalizacionesRepository extends JpaRepository<Personalizacion, Integer> {

}
