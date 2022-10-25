package sa.lendo.lendorestwithoauth.users.domain.mapper;

import sa.lendo.lendorestwithoauth.users.domain.AppUser;
import sa.lendo.lendorestwithoauth.users.domain.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO mapToDTO(AppUser user);

    AppUser mapToEntity(UserDTO userDTO);

}
