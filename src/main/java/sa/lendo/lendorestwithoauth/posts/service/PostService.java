package sa.lendo.lendorestwithoauth.ads.service;

import sa.lendo.lendorestwithoauth.ads.domain.dto.AdDTO;

import java.util.Set;

public interface AdService {
    AdDTO createAd(AdDTO ad);

    AdDTO findAdById(Long adId);

    AdDTO updateAd(AdDTO ad);

    void deleteAdById(Long adId);

    void deleteAd(AdDTO adDTO);

    Set<AdDTO> getUserAds(Long userId);

    Set<AdDTO> getHomePageTimeline();

    Set<AdDTO> findByTitleOrContent(String nameOrContent);
}
