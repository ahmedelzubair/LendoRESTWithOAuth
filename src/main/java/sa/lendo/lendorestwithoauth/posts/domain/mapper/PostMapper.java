package sa.lendo.lendorestwithoauth.ads.domain.mapper;

import sa.lendo.lendorestwithoauth.ads.domain.Ad;
import sa.lendo.lendorestwithoauth.ads.domain.dto.AdDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "user.id", target = "userId")
    AdDTO mapToDTO(Ad ad);

    @Mapping(source = "userId", target = "user.id")
    Ad mapToEntity(AdDTO adDTO);


}
