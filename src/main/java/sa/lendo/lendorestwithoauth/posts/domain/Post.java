package sa.lendo.lendorestwithoauth.ads.domain;


import sa.lendo.lendorestwithoauth.users.domain.AppUser;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity(name = "ads")
@Getter
@Setter
@ToString

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ad implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String mediaUrls;
    private Long impressions;
    @Enumerated(EnumType.STRING)
    private AdStatus status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppUser user;
    private String hashtags;

    private Timestamp createdAt;
    private Timestamp updatedAt;


}
