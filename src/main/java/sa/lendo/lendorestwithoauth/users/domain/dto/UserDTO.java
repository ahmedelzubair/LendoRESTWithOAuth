package sa.lendo.lendorestwithoauth.users.domain.dto;

import lombok.Data;
import sa.lendo.lendorestwithoauth.users.domain.UserGender;
import sa.lendo.lendorestwithoauth.users.domain.UserStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserDTO {

    private Long id;
    private String password;
    private String username;
    private String email;
    private String name;
    private UserGender gender;
    private UserStatus status;


}
