package sa.lendo.lendorestwithoauth.posts_comments.service;


import sa.lendo.lendorestwithoauth.posts_comments.domain.dto.CommentDTO;

import java.util.Set;

public interface CommentService {
    Set<CommentDTO> findPostCommentsByPostId(Long commentID);
    Set<CommentDTO> findAllComments();

}
