package sa.lendo.lendorestwithoauth.posts.domain.mapper;

import sa.lendo.lendorestwithoauth.posts.domain.Post;
import sa.lendo.lendorestwithoauth.posts.domain.dto.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "user.id", target = "userId")
    PostDTO mapToDTO(Post post);

    @Mapping(source = "userId", target = "user.id")
    Post mapToEntity(PostDTO postDTO);


}
