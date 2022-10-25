package sa.lendo.lendorestwithoauth.users.service;

import sa.lendo.lendorestwithoauth.users.domain.dto.UserDTO;
import sa.lendo.lendorestwithoauth.users.domain.dto.UserSignUpDTO;

import java.util.Set;

public interface UserService {

    UserDTO createUser(UserSignUpDTO userDTO);

    Set<UserDTO> findAll();
}
