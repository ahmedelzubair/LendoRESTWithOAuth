package sa.lendo.lendorestwithoauth.ads_comments.service;


import sa.lendo.lendorestwithoauth.ads_comments.domain.dto.AdCommentDTO;

import java.util.Set;

public interface AdCommentService {
    AdCommentDTO createAdComment(AdCommentDTO ad);

    Set<AdCommentDTO> findAdCommentsByAdId(Long commentID);

    AdCommentDTO findAdCommentsById(Long commentID);

    AdCommentDTO updateAdComment(AdCommentDTO ad);

    void deleteAdCommentById(Long commentID);

    void deleteAdComment(AdCommentDTO adDTO);

}
