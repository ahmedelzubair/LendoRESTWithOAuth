package sa.lendo.lendorestwithoauth.posts_comments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.lendo.lendorestwithoauth.posts_comments.domain.dto.CommentDTO;
import sa.lendo.lendorestwithoauth.posts_comments.service.CommentService;

import java.util.Set;

@RestController
@RequestMapping("api/v1/posts/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/{postId}")
    public ResponseEntity<Set<CommentDTO>> getPostComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.findPostCommentsByPostId(postId));
    }

    @GetMapping("")
    public ResponseEntity<Set<CommentDTO>> getAllComments() {
        return ResponseEntity.ok(commentService.findAllComments());
    }


}
