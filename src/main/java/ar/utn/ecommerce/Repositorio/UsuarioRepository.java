package ar.utn.ecommerce.Repositorio;

import ar.utn.ecommerce.models.Usuario.UsuarioCorriente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioCorriente, Integer> {

}
