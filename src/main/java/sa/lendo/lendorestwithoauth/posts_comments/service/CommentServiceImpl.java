package sa.lendo.lendorestwithoauth.ads_comments.service;


import sa.lendo.lendorestwithoauth.ads_comments.domain.AdComment;
import sa.lendo.lendorestwithoauth.ads_comments.domain.dto.AdCommentDTO;
import sa.lendo.lendorestwithoauth.ads_comments.domain.mapper.AdCommentMapper;
import sa.lendo.lendorestwithoauth.ads_comments.repo.AdCommentJpaRepo;
import sa.lendo.lendorestwithoauth.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdCommentServiceImpl implements AdCommentService {

    private final AdCommentJpaRepo adCommentJpaRepo;
    private final AdCommentMapper adCommentMapper;

    public AdCommentServiceImpl(AdCommentJpaRepo adCommentJpaRepo) {
        this.adCommentJpaRepo = adCommentJpaRepo;
        this.adCommentMapper = AdCommentMapper.INSTANCE;
    }


    @Override
    public AdCommentDTO createAdComment(AdCommentDTO adCommentDTO) {

        AdComment adCommentEntity = adCommentMapper.mapToEntity(adCommentDTO);

        AdComment savedAdComment = adCommentJpaRepo.save(adCommentEntity);

        return adCommentMapper.mapToDTO(savedAdComment);
    }

    @Override
    public Set<AdCommentDTO> findAdCommentsByAdId(Long adId) {

        Set<AdComment> adComments = adCommentJpaRepo.findAllByAd_IdOrderByCreatedAtDesc(adId);

        if (adComments.isEmpty()) {
            throw new EntityNotFoundException("AdComment with id " + adId + " does not exist");
        }

        return adComments.stream().map(adCommentMapper::mapToDTO).collect(Collectors.toSet());
    }

    @Override
    public AdCommentDTO findAdCommentsById(Long commentID) {

        Optional<AdComment> adComment = adCommentJpaRepo.findById(commentID);

        if (adComment.isEmpty()) {
            throw new EntityNotFoundException("AdComment with id " + commentID + " does not exist");
        }

        return adCommentMapper.mapToDTO(adComment.get());
    }

    @Override
    public AdCommentDTO updateAdComment(AdCommentDTO commentDTO) {

        AdComment adCommentEntity = adCommentMapper.mapToEntity(commentDTO);

        Optional<AdComment> adOptional = adCommentJpaRepo.findById(adCommentEntity.getId());

        if (adOptional.isEmpty()) {
            throw new EntityNotFoundException("AdComment with id " + adCommentEntity.getId()
                    + " does not exist");
        }

        AdComment updatedAdComment = adCommentJpaRepo.save(adCommentEntity);

        return adCommentMapper.mapToDTO(updatedAdComment);
    }

    @Override
    public void deleteAdCommentById(Long adCommentId) {

        Optional<AdComment> adOptional = adCommentJpaRepo.findById(adCommentId);

        if (adOptional.isEmpty()) {
            throw new EntityNotFoundException("AdComment with id " + adCommentId
                    + " does not exist");
        }

        adCommentJpaRepo.delete(adOptional.get());
    }

    @Override
    public void deleteAdComment(AdCommentDTO adCommentDTO) {

        Optional<AdComment> adOptional = adCommentJpaRepo.findById(adCommentDTO.getId());

        if (adOptional.isEmpty()) {
            throw new EntityNotFoundException("AdComment with id " + adCommentDTO.getId()
                    + " does not exist");
        }

        adCommentJpaRepo.delete(adOptional.get());
    }


}
