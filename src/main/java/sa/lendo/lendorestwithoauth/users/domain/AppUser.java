package sa.lendo.lendorestwithoauth.users.domain;


import lombok.*;
import org.apache.tomcat.jni.Address;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "users")
@Getter
@Setter
@ToString

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser implements Serializable {
    //
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(nullable = false, unique = true)
    private String username;
    private String phoneNumber;
    private String profileImageUrl;
    private String coverImageUrl;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String about;
    private String roles;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AppUser appUser = (AppUser) o;
        return getId() != null && Objects.equals(getId(), appUser.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
