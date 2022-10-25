package sa.lendo.lendorestwithoauth.posts.service;

import org.springframework.stereotype.Service;
import sa.lendo.lendorestwithoauth.exceptions.EntityNotFoundException;
import sa.lendo.lendorestwithoauth.posts.domain.Post;
import sa.lendo.lendorestwithoauth.posts.domain.dto.PostDTO;
import sa.lendo.lendorestwithoauth.posts.domain.mapper.PostMapper;
import sa.lendo.lendorestwithoauth.posts.repo.PostJpaRepo;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostJpaRepo postJpaRepo;
    private final PostMapper postMapper;

    public PostServiceImpl(PostJpaRepo postJpaRepo) {
        this.postJpaRepo = postJpaRepo;
        this.postMapper = PostMapper.INSTANCE;
    }


    @Override
    public Set<PostDTO> findAllPostsByUserId(Long userId) {

        Optional<Set<Post>> posts = postJpaRepo.findPostsByUserId(userId);

        if (posts.isEmpty()) {
            throw new EntityNotFoundException("Posts with user id " + userId + " does not exist");
        }

        return posts.get().stream().map(postMapper::mapToDTO).collect(Collectors.toSet());
    }


    @Override
    public Set<PostDTO> findAllPosts() {
        List<Post> posts = postJpaRepo.findAll();

        if (posts.isEmpty()) {
            throw new EntityNotFoundException("There is no posts yet!");
        }

        return posts.stream().map(postMapper::mapToDTO)
                .collect(Collectors.toSet());
    }

}
