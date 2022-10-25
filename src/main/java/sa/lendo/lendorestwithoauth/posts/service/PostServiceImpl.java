package sa.lendo.lendorestwithoauth.ads.service;

import sa.lendo.lendorestwithoauth.ads.domain.Ad;
import sa.lendo.lendorestwithoauth.ads.domain.dto.AdDTO;
import sa.lendo.lendorestwithoauth.ads.domain.mapper.AdMapper;
import sa.lendo.lendorestwithoauth.ads.repo.AdJpaRepo;
import sa.lendo.lendorestwithoauth.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private final AdJpaRepo adJpaRepo;
    private final AdMapper adMapper;

    public AdServiceImpl(AdJpaRepo adJpaRepo) {
        this.adJpaRepo = adJpaRepo;
        this.adMapper = AdMapper.INSTANCE;
    }


    @Override
    public AdDTO createAd(AdDTO ad) {

        Ad adEntity = adMapper.mapToEntity(ad);

        Ad savedAd = adJpaRepo.save(adEntity);

        return adMapper.mapToDTO(savedAd);
    }

    @Override
    public AdDTO findAdById(Long adId) {

        Optional<Ad> adEntity = adJpaRepo.findById(adId);

        if (adEntity.isEmpty()) {
            throw new EntityNotFoundException("Ad with id " + adId + " does not exist");
        }

        return adMapper.mapToDTO(adEntity.get());
    }

    @Override
    public AdDTO updateAd(AdDTO ad) {

        Ad adEntity = adMapper.mapToEntity(ad);

        Optional<Ad> adOptional = adJpaRepo.findById(adEntity.getId());

        if (adOptional.isEmpty()) {
            throw new EntityNotFoundException("Ad with id " + adEntity.getId()
                    + " does not exist");
        }

        adEntity.setUser(adOptional.get().getUser());
        Ad updatedAd = adJpaRepo.save(adEntity);

        return adMapper.mapToDTO(updatedAd);
    }

    @Override
    public void deleteAdById(Long adId) {

        Optional<Ad> adOptional = adJpaRepo.findById(adId);

        if (adOptional.isEmpty()) {
            throw new EntityNotFoundException("Ad with id " + adId
                    + " does not exist");
        }

        adJpaRepo.delete(adOptional.get());
    }

    @Override
    public void deleteAd(AdDTO adDTO) {

        Optional<Ad> adOptional = adJpaRepo.findById(adDTO.getId());

        if (adOptional.isEmpty()) {
            throw new EntityNotFoundException("Ad with id " + adDTO.getId()
                    + " does not exist");
        }

        adJpaRepo.delete(adOptional.get());
    }

    @Override
    public Set<AdDTO> getUserAds(Long userId) {

        Optional<Set<Ad>> adOptional = adJpaRepo.findAdsByUserIdOrderByCreatedAtDesc(userId);

        if (adOptional.isEmpty()) {
            throw new EntityNotFoundException("Ad with user id " + userId
                    + " does not exist");
        }

        return adOptional.get().stream().map(adMapper::mapToDTO)
                .collect(Collectors.toSet());
    }


    @Override
    public Set<AdDTO> getHomePageTimeline() {
        List<Ad> homePageAds = adJpaRepo.findAll();

        if (homePageAds.isEmpty()) {
            throw new EntityNotFoundException("There is no ads yet!");
        }

        return homePageAds.stream().map(adMapper::mapToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AdDTO> findByTitleOrContent(String titleOrContent) {


        Optional<Set<Ad>> adOptional =
                adJpaRepo.findAdsByTitleOrContentOrderByCreatedAtDesc(titleOrContent, titleOrContent);

        if (adOptional.isEmpty()) {
            throw new EntityNotFoundException("Ad with title or content " + titleOrContent
                    + " does not exist");
        }

        return adOptional.get().stream().map(adMapper::mapToDTO)
                .collect(Collectors.toSet());

    }
}
