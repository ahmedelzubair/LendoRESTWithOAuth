package sa.lendo.lendorestwithoauth.ads_comments.domain.mapper;

import sa.lendo.lendorestwithoauth.ads_comments.domain.AdComment;
import sa.lendo.lendorestwithoauth.ads_comments.domain.dto.AdCommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdCommentMapper {

    AdCommentMapper INSTANCE = Mappers.getMapper(AdCommentMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "ad.id", target = "adId")
    AdCommentDTO mapToDTO(AdComment adComment);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "adId", target = "ad.id")
    AdComment mapToEntity(AdCommentDTO adCommentDTO);

}
