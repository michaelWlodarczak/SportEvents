package entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("PLAYER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Player extends User {

    private String firstName;
    private String lastName;
    LocalDate dateOfBirth;
    private String teamName;
    private double weight;
    private String additionalInfo;
    private String license;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private List<Subscription> playerSubscriptions;


    public Player(UUID userId, String login, String password, String email, UserType userType,
                  String streetWithNumber, String city, String country, String zipCode, String firstName,
                  String lastName, LocalDate dateOfBirth, String teamName, double weight, String additionalInfo,
                  String license, List<Subscription> subscriptionList) {
        super(userId, login, password, email, userType, streetWithNumber, city, country, zipCode);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.teamName = teamName;
        this.weight = weight;
        this.additionalInfo = additionalInfo;
        this.license = license;
        this.playerSubscriptions = playerSubscriptions;
    }
}
