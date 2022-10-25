package sa.lendo.lendorestwithoauth.users.domain.dto;

import lombok.Data;
import sa.lendo.lendorestwithoauth.users.domain.UserStatus;

@Data
public class UserLoginDTO {

    private String password;
    private String username;

}
