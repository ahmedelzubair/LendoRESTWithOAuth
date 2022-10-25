package sa.lendo.lendorestwithoauth.users.domain.dto;

import lombok.Data;
import sa.lendo.lendorestwithoauth.users.domain.UserGender;
import sa.lendo.lendorestwithoauth.users.domain.UserStatus;

@Data
public class UserSignUpDTO {

    private Long id;
    private String password;
    private String username;
    private String email;
    private String name;
    private UserGender gender;
    private UserStatus status;


}
