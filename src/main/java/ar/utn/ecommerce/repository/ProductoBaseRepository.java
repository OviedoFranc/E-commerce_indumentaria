package ar.utn.ecommerce.repository;

import ar.utn.ecommerce.models.Productos.Categoria;
import ar.utn.ecommerce.models.Productos.EstadoProducto;
import ar.utn.ecommerce.models.Productos.ProductoBase;
import ar.utn.ecommerce.models.projection.DTOProdPersoPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoBaseRepository extends JpaRepository<ProductoBase, Integer> {

    public Page<ProductoBase> findByEstadoProducto(EstadoProducto estado,Pageable pageable);
    public ProductoBase findByEstadoProducto(EstadoProducto estado);

    public List<ProductoBase> findByCategoria(Categoria categoria);
}
