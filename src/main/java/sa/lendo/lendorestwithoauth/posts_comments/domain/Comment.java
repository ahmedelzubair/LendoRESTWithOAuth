package sa.lendo.lendorestwithoauth.posts_comments.domain;

import sa.lendo.lendorestwithoauth.posts.domain.Post;
import sa.lendo.lendorestwithoauth.users.domain.AppUser;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "posts_comments")
@Getter
@Setter
@ToString

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private String name;
    private String email;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id",referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;



}
