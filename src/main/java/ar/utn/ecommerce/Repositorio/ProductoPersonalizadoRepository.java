package ar.utn.ecommerce.Repositorio;


import ar.utn.ecommerce.models.DTO.DTOProdPersoResume;
import ar.utn.ecommerce.models.Productos.EstadoProducto;
import ar.utn.ecommerce.models.Productos.ProductoPersonalizado;
import ar.utn.ecommerce.models.Usuario.Vendedor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductoPersonalizadoRepository extends JpaRepository<ProductoPersonalizado, Integer> {


    Page <List<DTOProdPersoResume>> findAllByEstadoProducto(EstadoProducto estado, Pageable pageable);
    //List<ProductoPersonalizado> findByCategoria(Categoria categoria);
    List<ProductoPersonalizado> findByCreador(String creador);

}