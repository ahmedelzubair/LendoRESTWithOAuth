package sa.lendo.lendorestwithoauth.posts.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.lendo.lendorestwithoauth.posts.domain.Post;

import java.util.Optional;
import java.util.Set;

public interface PostJpaRepo extends JpaRepository<Post, Long> {
    Optional<Set<Post>> findPostsByUserId(Long userId);
}
