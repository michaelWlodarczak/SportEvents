package entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String login;
    private String password;
    private String email;

    @Column(name = "user_type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String streetWithNumber;
    private String city;
    private String country;
    private String zipCode;

    public User(@NotNull String login,
                @NotNull String password,
                @NotNull String email,
                String streetWithNumber,
                String city,
                String country,
                String zipCode) {
        this.userId = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.email = email;
        this.streetWithNumber = streetWithNumber;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }
}
