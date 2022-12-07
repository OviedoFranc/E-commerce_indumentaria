package ar.utn.ecommerce.repository;


import ar.utn.ecommerce.models.Productos.Categoria;
import ar.utn.ecommerce.models.Productos.EstadoProducto;
import ar.utn.ecommerce.models.Productos.ProductoBase;
import ar.utn.ecommerce.models.Productos.ProductoPersonalizado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//@RepositoryRestResource(path = "Producto")
@Repository
public interface ProductoPersonalizadoRepository extends JpaRepository<ProductoPersonalizado, Integer> {

    public Page<ProductoPersonalizado> findByEstadoProducto(EstadoProducto estado, Pageable pageable);
    public List<ProductoPersonalizado> findByCategoria(Categoria categoria);
}