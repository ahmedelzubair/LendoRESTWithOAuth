package sa.lendo.lendorestwithoauth.ads_comments.domain.dto;

import sa.lendo.lendorestwithoauth.ads_comments.domain.AdCommentStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data

public class AdCommentDTO {

    private Long id;
    private String content;
    private Long userId;
    private Long adId;
    private String hashtags;
    private AdCommentStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
