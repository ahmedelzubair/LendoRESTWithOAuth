package sa.lendo.lendorestwithoauth.ads.repo;

import sa.lendo.lendorestwithoauth.ads.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface AdJpaRepo extends JpaRepository<Ad, Long> {
    Optional<Set<Ad>> findAdsByUserIdOrderByCreatedAtDesc(Long ownerId);

    Optional<Set<Ad>> findAdsByTitleOrContentOrderByCreatedAtDesc(String title, String content);

}
