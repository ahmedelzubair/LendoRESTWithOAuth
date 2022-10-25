package sa.lendo.lendorestwithoauth.posts.service;

import sa.lendo.lendorestwithoauth.posts.domain.dto.PostDTO;

import java.util.Set;

public interface PostService {

    Set<PostDTO> findAllPostsByUserId(Long postId);

    Set<PostDTO> findAllPosts();


}
