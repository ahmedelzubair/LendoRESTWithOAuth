package sa.lendo.lendorestwithoauth.ads_comments.domain;

import sa.lendo.lendorestwithoauth.ads.domain.Ad;
import sa.lendo.lendorestwithoauth.users.domain.AppUser;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "ads_comments")
@Getter
@Setter
@ToString

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdComment implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ad_id",referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ad ad;

    @Enumerated(EnumType.STRING)
    private AdCommentStatus status;

    private Timestamp createdAt;
    private Timestamp updatedAt;


}
