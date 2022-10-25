package sa.lendo.lendorestwithoauth.posts_comments.service;


import org.springframework.stereotype.Service;
import sa.lendo.lendorestwithoauth.exceptions.EntityNotFoundException;
import sa.lendo.lendorestwithoauth.posts_comments.domain.Comment;
import sa.lendo.lendorestwithoauth.posts_comments.domain.dto.CommentDTO;
import sa.lendo.lendorestwithoauth.posts_comments.domain.mapper.CommentMapper;
import sa.lendo.lendorestwithoauth.posts_comments.repo.CommentJpaRepo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentJpaRepo commentJpaRepo;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentJpaRepo commentJpaRepo) {
        this.commentJpaRepo = commentJpaRepo;
        this.commentMapper = CommentMapper.INSTANCE;
    }


    @Override
    public Set<CommentDTO> findPostCommentsByPostId(Long postId) {

        Set<Comment> comments = commentJpaRepo.findAllByPostId(postId);

        if (comments.isEmpty()) {
            throw new EntityNotFoundException("PostComment with id " + postId + " does not exist");
        }

        return comments.stream().map(commentMapper::mapToDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<CommentDTO> findAllComments() {
        List<Comment> comments = commentJpaRepo.findAll();

        if (comments.isEmpty()) {
            throw new EntityNotFoundException("No comments available yet");
        }

        return comments.stream().map(commentMapper::mapToDTO).collect(Collectors.toSet());
    }


}
