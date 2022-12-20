package ar.utn.ecommerce.Repositorio;

import ar.utn.ecommerce.Modelos.Usuario.UsuarioCorriente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioCorriente, Integer> {
    Optional<UsuarioCorriente>  findByEmail(String email);
}
