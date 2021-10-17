package entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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


    public Player(String login,
                  String password,
                  String email,
                  String streetWithNumber,
                  String city,
                  String country,
                  String zipCode,
                  String firstName,
                  String lastName,
                  LocalDate dateOfBirth,
                  String teamName,
                  double weight,
                  String additionalInfo,
                  String license,
                  List<Subscription> playerSubscriptions) {
        super(login, password, email, streetWithNumber, city, country, zipCode);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.teamName = teamName;
        this.weight = weight;
        this.additionalInfo = additionalInfo;
        this.license = license;
        this.playerSubscriptions = playerSubscriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Double.compare(player.getWeight(),
                getWeight()) == 0 && Objects.equals(getFirstName(),
                player.getFirstName()) && Objects.equals(getLastName(),
                player.getLastName()) && Objects.equals(getDateOfBirth(),
                player.getDateOfBirth()) && Objects.equals(getTeamName(),
                player.getTeamName()) && Objects.equals(getAdditionalInfo(),
                player.getAdditionalInfo()) && Objects.equals(getLicense(),
                player.getLicense()) && Objects.equals(getPlayerSubscriptions(),
                player.getPlayerSubscriptions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(),
                getLastName(),
                getDateOfBirth(),
                getTeamName(),
                getWeight(),
                getAdditionalInfo(),
                getLicense(),
                getPlayerSubscriptions());
    }

//    //przeniesienie tej metody statycznej z klasy CustomerService
//    public static Person createWith(RegisterPersonForm form){
//        return new Person(form.getEmail(),
//                form.getFirstName(),
//                form.getLastName(),
//                form.getPesel());
//    }


}
