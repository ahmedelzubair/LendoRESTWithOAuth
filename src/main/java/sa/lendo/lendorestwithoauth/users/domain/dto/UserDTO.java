package sa.lendo.lendorestwithoauth.users.domain.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserDTO {

    private Long id;
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private String profileImageUrl;
    private String coverImageUrl;
    private LocalDate dateOfBirth;
    private Long addressId;
    private String about;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}
