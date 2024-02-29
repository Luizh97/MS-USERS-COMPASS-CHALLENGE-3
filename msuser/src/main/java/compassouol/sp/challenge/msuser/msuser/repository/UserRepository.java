package compassouol.sp.challenge.msuser.msuser.repository;

import compassouol.sp.challenge.msuser.msuser.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);

    @Query("SELECT u.role FROM Usuario u WHERE u.email like :username")
    Usuario.Role findRoleByEmail(String username);
}
