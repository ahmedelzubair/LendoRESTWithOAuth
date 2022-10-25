package sa.lendo.lendorestwithoauth.posts_comments.domain.mapper;

import sa.lendo.lendorestwithoauth.posts_comments.domain.Comment;
import sa.lendo.lendorestwithoauth.posts_comments.domain.dto.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "post.id", target = "postId")
    CommentDTO mapToDTO(Comment comment);

    @Mapping(source = "postId", target = "post.id")
    Comment mapToEntity(CommentDTO commentDTO);

}
