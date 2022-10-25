package sa.lendo.lendorestwithoauth.posts.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private Integer userId;
    private String title;
    private String body;
}
