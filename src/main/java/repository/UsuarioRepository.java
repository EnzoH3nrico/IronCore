package repository;

import model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AutenticacaoRepository extends JpaRepository<Usuario, Long> {
}
