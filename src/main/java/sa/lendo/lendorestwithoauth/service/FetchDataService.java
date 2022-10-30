package sa.lendo.lendorestwithoauth.service;

import sa.lendo.lendorestwithoauth.domain.Comment;
import sa.lendo.lendorestwithoauth.domain.Post;
import sa.lendo.lendorestwithoauth.domain.User;

import java.util.Set;

public interface FetchDataService {

    Set<Post> fetchUserPosts(Long userId);

    Set<Post> fetchAllPosts();

    Set<Comment> fetchAllComments();

    Set<Comment> fetchPostComments(Long postId);

    Set<User> fetchUsers();


}
