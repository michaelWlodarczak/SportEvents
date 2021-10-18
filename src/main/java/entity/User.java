package entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class User {
    @Id
    private UUID userId;

    private String userLogin;
    private String userPassword;
    private String userEmail;

    @Column(name = "user_type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String userStreet;
    private String userCity;
    private String userCountry;
    private String userZipCode;

    public User(@NotNull String userLogin,
                @NotNull String userPassword,
                @NotNull String userEmail,
                String userStreet,
                String userCity,
                String userCountry,
                String userZipCode) {
        this.userId = UUID.randomUUID();
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userStreet = userStreet;
        this.userCity = userCity;
        this.userCountry = userCountry;
        this.userZipCode = userZipCode;
    }

    public abstract String getName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId.equals(user.userId) && userEmail.equals(user.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userEmail);
    }
}
