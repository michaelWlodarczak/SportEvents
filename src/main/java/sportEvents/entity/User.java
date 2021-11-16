package sportEvents.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import sportEvents.entity.enums.UserType;
import sportEvents.service.dto.UserView;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public abstract class User {
    @Id
    @Type(type="uuid-char")
    private UUID userId;
    @Column(name = "user_type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String userPassword;
    @Column(unique=true)
    private String userLogin;
    private String userEmail;
    private String userCity;
    private String userStreet;
    private String userCountry;
    private String userZipCode;
    private boolean userActive;
    @ElementCollection(targetClass = String.class)
    private List<String> userRoles;

    public User(@NonNull String userPassword,
                @NonNull String userLogin,
                @NonNull String userEmail,
                String userCity,
                String userStreet,
                String userCountry,
                String userZipCode) {
        this.userId = UUID.randomUUID();
        this.userPassword = userPassword;
        this.userLogin = userLogin;
        this.userEmail = userEmail;
        this.userCity = userCity;
        this.userStreet = userStreet;
        this.userCountry = userCountry;
        this.userZipCode = userZipCode;
        this.userActive = true;
        this.userRoles = new ArrayList<>(Arrays.asList("ROLE_USER"));
    }

    public abstract String getName();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) && userEmail.equals(user.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userEmail);
    }

    public void setUserActive(boolean active) {
        this.userActive = active;
    }

    public UserView toUserView(){
        return new UserView(getUserId(),
                getName(),
                getUserEmail(),
                getUserType(),
                isUserActive());
    }

}


