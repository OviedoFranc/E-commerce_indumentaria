package ar.utn.ecommerce.Repositorio;


import ar.utn.ecommerce.Modelos.DTO.DTOProdPersoResume;
import ar.utn.ecommerce.Modelos.Productos.EstadoProducto;
import ar.utn.ecommerce.Modelos.Productos.ProductoPersonalizado;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductoPersonalizadoRepository extends JpaRepository<ProductoPersonalizado, Integer> {


    Page <List<DTOProdPersoResume>> findAllByEstadoProducto(EstadoProducto estado, Pageable pageable);
    //List<ProductoPersonalizado> findByCategoria(Categoria categoria);
    List<ProductoPersonalizado> findByCreador(String creador);

}