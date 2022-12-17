package ar.utn.ecommerce.Repositorio;

import ar.utn.ecommerce.models.Productos.Categoria;
import ar.utn.ecommerce.models.Productos.EstadoProducto;
import ar.utn.ecommerce.models.Productos.ProductoBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoBaseRepository extends JpaRepository<ProductoBase, Integer> {

   //Page<ProductoBase> findByEstadoProducto(EstadoProducto estado,Pageable pageable);

    ProductoBase findByEstadoProducto(EstadoProducto estado);
    List<ProductoBase> findByCategoria(Categoria categoria);
}
