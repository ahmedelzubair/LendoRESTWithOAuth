package sa.lendo.lendorestwithoauth.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment implements Serializable {


    private Long id;
    private String body;
    private String name;
    private String email;
    private Long post_id;


}
