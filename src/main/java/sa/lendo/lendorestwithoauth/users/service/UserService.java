package sa.lendo.lendorestwithoauth.users.service;

import sa.lendo.lendorestwithoauth.users.domain.dto.UserDTO;

import java.util.Set;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO appUser);

    void deleteUser(UserDTO appUser);

    UserDTO findUserByEmail(String email);

    UserDTO findUserById(Long id);

    void changePassword(String email, String oldPassword, String newPassword);


    void deleteUserById(Long id);

    Set<UserDTO> findAll();
}
