package sa.lendo.lendorestwithoauth.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sa.lendo.lendorestwithoauth.domain.Comment;
import sa.lendo.lendorestwithoauth.domain.Post;
import sa.lendo.lendorestwithoauth.domain.User;
import sa.lendo.lendorestwithoauth.exceptions.EntityNotFoundException;

import java.util.Optional;
import java.util.Set;

@Service
public class FetchDataServiceImpl implements FetchDataService {

    private static final String BASE_API_URL = "https://gorest.co.in/public/v2";
    private final RestTemplate restTemplate;

    public FetchDataServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    @Override
    public Set<User> fetchUsers() {
        User[] users = restTemplate.getForObject(BASE_API_URL + "/users", User[].class);
        if (users == null) {
            throw new EntityNotFoundException("There is no users yet!");
        }
        return Set.of(Optional.of(users).orElse(new User[0]));
    }

    @Override
    public Set<Post> fetchAllPosts() {
        Post[] posts = restTemplate.getForObject(BASE_API_URL + "/posts", Post[].class);
        if (posts == null) {
            throw new EntityNotFoundException("Posts not found");
        }
        return Set.of(Optional.of(posts).orElse(new Post[0]));
    }

    @Override
    public Set<Post> fetchUserPosts(Long userId) {
        Post[] posts = restTemplate.getForObject(BASE_API_URL + "/users/" + userId + "/posts", Post[].class);
        if (posts == null) {
            throw new EntityNotFoundException("No posts found for user with id: " + userId );
        }
        return Set.of(posts);
    }

    @Override
    public Set<Comment> fetchAllComments() {
        Comment[] comments = restTemplate.getForObject(BASE_API_URL + "/comments", Comment[].class);
        if (comments == null) {
            throw new EntityNotFoundException("Comments not found");
        }
        return Set.of(Optional.of(comments).orElse(new Comment[0]));
    }

    @Override
    public Set<Comment> fetchPostComments(Long postId) {
        Comment[] comments = restTemplate.getForObject(BASE_API_URL + "/posts/" + postId + "/comments", Comment[].class);
        if (comments == null) {
            throw new EntityNotFoundException("No comments found");
        }
        return Set.of(Optional.of(comments).orElse(new Comment[0]));
    }


}
