package sa.lendo.lendorestwithoauth.posts_comments.repo;

import sa.lendo.lendorestwithoauth.posts_comments.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CommentJpaRepo extends JpaRepository<Comment, Long> {

    Set<Comment> findAllByPostId(Long postId);

}
