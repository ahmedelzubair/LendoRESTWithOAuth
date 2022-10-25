package sa.lendo.lendorestwithoauth.ads_comments.controller;

import sa.lendo.lendorestwithoauth.ads_comments.domain.dto.AdCommentDTO;
import sa.lendo.lendorestwithoauth.ads_comments.service.AdCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

// TODO : implement and all ads controller methods
@RestController
@RequestMapping("api/v1/ads-comments")
public class AdCommentController {

    private final AdCommentService adCommentService;

    public AdCommentController(AdCommentService adCommentService) {
        this.adCommentService = adCommentService;
    }


    @GetMapping("/ads/{adId}")
    public ResponseEntity<Set<AdCommentDTO>> getAdComments(@PathVariable Long adId) {
        return ResponseEntity.ok(adCommentService.findAdCommentsByAdId(adId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdCommentDTO> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(adCommentService.findAdCommentsById(id));
    }

    @PostMapping("")
    public ResponseEntity<AdCommentDTO> createComment(@RequestBody AdCommentDTO adDTO) {
        AdCommentDTO createdComment = adCommentService.createAdComment(adDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdComment.getId()).toUri();

        return ResponseEntity.created(location).body(createdComment);
    }

    @PutMapping("")
    public ResponseEntity<AdCommentDTO> updateComment(@RequestBody AdCommentDTO adDTO) {
        return ResponseEntity.ok(adCommentService.updateAdComment(adDTO));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteComment(@RequestBody AdCommentDTO adDTO) {
        adCommentService.deleteAdComment(adDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long id) {
        adCommentService.deleteAdCommentById(id);
        return ResponseEntity.ok().build();
    }



}
