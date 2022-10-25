package sa.lendo.lendorestwithoauth.ads.controller;

import sa.lendo.lendorestwithoauth.ads.domain.dto.AdDTO;
import sa.lendo.lendorestwithoauth.ads.service.AdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

// TODO : implement and all ads controller methods
@RestController
@RequestMapping("api/v1/ads")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }


    @GetMapping("")
    public ResponseEntity<Set<AdDTO>> getHomePageAds() {
        return ResponseEntity.ok(adService.getHomePageTimeline());
    }

    @PostMapping("")
    public ResponseEntity<AdDTO> createAd(@RequestBody AdDTO adDTO) {
        AdDTO createdAd = adService.createAd(adDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdAd.getId()).toUri();

        return ResponseEntity.created(location).body(createdAd);
    }

    @PutMapping("")
    public ResponseEntity<AdDTO> updateAd(@RequestBody AdDTO adDTO) {
        return ResponseEntity.ok(adService.updateAd(adDTO));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAd(@RequestBody AdDTO adDTO) {
        adService.deleteAd(adDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdById(@PathVariable Long id) {
        adService.deleteAdById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdDTO> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(adService.findAdById(id));
    }


    @GetMapping("/{titleOrContent}")
    public ResponseEntity<Set<AdDTO>> searchAdsByTitleOrBody(@PathVariable String titleOrContent) {

        return ResponseEntity.ok(adService.findByTitleOrContent(titleOrContent));
    }

}
