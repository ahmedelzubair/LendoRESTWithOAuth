package sa.lendo.lendorestwithoauth.posts_comments.domain.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;

}
