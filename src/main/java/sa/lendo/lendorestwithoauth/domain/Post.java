package sa.lendo.lendorestwithoauth.domain;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post implements Serializable {

    private Long id;
    private String title;
    private String body;
    private Long user_id;


}
