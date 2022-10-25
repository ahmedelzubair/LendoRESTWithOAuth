package sa.lendo.lendorestwithoauth.users.repo;

import sa.lendo.lendorestwithoauth.users.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);

    Optional<AppUser> findByUsername(String username);

    AppUser findByUsernameOrEmail(String username, String email);


}
