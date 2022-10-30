package sa.lendo.lendorestwithoauth.domain;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    private Long id;
    private String email;
    private String name;
    private String gender;
    private String status;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
