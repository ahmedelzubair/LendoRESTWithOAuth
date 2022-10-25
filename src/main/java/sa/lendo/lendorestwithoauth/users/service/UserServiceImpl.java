package sa.lendo.lendorestwithoauth.users.service;

import sa.lendo.lendorestwithoauth.exceptions.EntityNotFoundException;
import sa.lendo.lendorestwithoauth.exceptions.EntityNotSavedException;
import sa.lendo.lendorestwithoauth.users.domain.AppUser;
import sa.lendo.lendorestwithoauth.users.domain.dto.UserDTO;
import sa.lendo.lendorestwithoauth.users.domain.dto.UserSignUpDTO;
import sa.lendo.lendorestwithoauth.users.domain.mapper.UserMapper;
import sa.lendo.lendorestwithoauth.users.repo.UserJpaRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepo userJpaRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserJpaRepo userJpaRepo, PasswordEncoder passwordEncoder) {
        this.userJpaRepo = userJpaRepo;
        this.userMapper = UserMapper.INSTANCE;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Set<UserDTO> findAll() {

        List<AppUser> homePageCategories = userJpaRepo.findAll();

        if (homePageCategories.isEmpty()) {
            throw new EntityNotFoundException("There is no users yet!");
        }

        return homePageCategories.stream().map(userMapper::mapToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public UserDTO createUser(UserSignUpDTO userDTO) {

        AppUser user = userMapper.mapToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser savedUser = userJpaRepo.save(user);

        UserDTO savedUserDTO = userMapper.mapToDTO(savedUser);
        if (savedUserDTO == null) {
            throw new EntityNotSavedException("User not saved");
        }
        return savedUserDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO appUser) {

        AppUser user = userMapper.mapToEntity(appUser);

        Optional<AppUser> userOptional = userJpaRepo.findById(user.getId());

        if (userOptional.isEmpty()) {
            throw new EntityNotSavedException("Requested user not found");
        }

        AppUser updatedUser = userJpaRepo.save(user);

        return userMapper.mapToDTO(updatedUser);
    }

    @Override
    public void deleteUser(UserDTO appUser) {

        AppUser user = userMapper.mapToEntity(appUser);

        Optional<AppUser> userOptional = userJpaRepo.findById(user.getId());

        if (userOptional.isEmpty()) {
            throw new EntityNotSavedException("Requested user not found");
        }

        userJpaRepo.delete(user);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        AppUser user = userJpaRepo.findByEmail(email);
        if (user == null) {
            throw new EntityNotSavedException("User not found");
        }
        return userMapper.mapToDTO(user);
    }

    @Override
    public UserDTO findUserById(Long id) {
        Optional<AppUser> userOptional = userJpaRepo.findById(id);
        if (userOptional.isEmpty()) {
            throw new EntityNotSavedException("User not found");
        }
        return userMapper.mapToDTO(userOptional.get());
    }

    @Override
    public void changePassword(String email, String oldPassword, String newPassword) {

    }

    @Override
    public void deleteUserById(Long id) {

        Optional<AppUser> userOptional = userJpaRepo.findById(id);

        if (userOptional.isEmpty()) {
            throw new EntityNotSavedException("Requested user not found");
        }

        userJpaRepo.deleteById(id);
    }

}
