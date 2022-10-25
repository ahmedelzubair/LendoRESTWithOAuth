package sa.lendo.lendorestwithoauth.ads_comments.repo;

import sa.lendo.lendorestwithoauth.ads_comments.domain.AdComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AdCommentJpaRepo extends JpaRepository<AdComment, Long> {

    Set<AdComment> findAllByAd_IdOrderByCreatedAtDesc(Long adId);

}
