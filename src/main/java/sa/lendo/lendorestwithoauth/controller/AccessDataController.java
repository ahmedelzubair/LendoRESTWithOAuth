package sa.lendo.lendorestwithoauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.lendo.lendorestwithoauth.domain.Comment;
import sa.lendo.lendorestwithoauth.domain.Post;
import sa.lendo.lendorestwithoauth.domain.User;
import sa.lendo.lendorestwithoauth.service.FetchDataService;

import java.util.Set;

@RestController
@RequestMapping("api/v1")
public class AccessDataController {

    private final FetchDataService fetchDataService;

    public AccessDataController(FetchDataService fetchDataService) {
        this.fetchDataService = fetchDataService;
    }

    @GetMapping("/users")
    public ResponseEntity<Set<User>> getAllUsers() {
        return ResponseEntity.ok(fetchDataService.fetchUsers());
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<Set<Post>> getAllUserPosts(@PathVariable Long userId) {
        return ResponseEntity.ok(fetchDataService.fetchUserPosts(userId));
    }


    @GetMapping("/posts")
    public ResponseEntity<Set<Post>> getAllPosts() {
        return ResponseEntity.ok(fetchDataService.fetchAllPosts());
    }


    @GetMapping("/comments")
    public ResponseEntity<Set<Comment>> getAllComments() {
        return ResponseEntity.ok(fetchDataService.fetchAllComments());
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<Set<Comment>> getPostComments(@PathVariable Long postId) {
        return ResponseEntity.ok(fetchDataService.fetchPostComments(postId));
    }


}
