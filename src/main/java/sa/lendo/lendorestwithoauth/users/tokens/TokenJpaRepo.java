package sa.lendo.lendorestwithoauth.users.tokens;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.lendo.lendorestwithoauth.users.domain.UserToken;

import java.util.Optional;

public interface TokenJpaRepo extends JpaRepository<UserToken, Long> {
    Optional<UserToken> findByUsername(String  username);

    Optional<UserToken> findByAccessToken(String code);

    Optional<UserToken> findRefreshTokenByAccessToken(String accessToken);


}
