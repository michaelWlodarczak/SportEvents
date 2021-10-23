package entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import service.dto.RegisterPlayerForm;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("PLAYER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Player extends User {

    private String playerFirstName;
    private String playerLastName;
    LocalDate playerDOB;
    private String playerTeamName;
    private double playerWeight;
    private String playerAdditionalInfo;
    private String playerLicense;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private List<Subscription> playerSubscriptions;

    public Player(String userLogin,
                  String userPassword,
                  String userEmail,
                  String userStreet,
                  String userCity,
                  String userCountry,
                  String userZipCode,
                  @NonNull String playerFirstName,
                  @NonNull String playerLastName,
                  @NonNull LocalDate playerDOB,
                  String playerTeamName,
                  double playerWeight,
                  String playerAdditionalInfo,
                  String playerLicense,
                  List<Subscription> playerSubscriptions) {
        super(userLogin, userPassword, userEmail, userStreet, userCity, userCountry, userZipCode);
        this.playerFirstName = playerFirstName;
        this.playerLastName = playerLastName;
        this.playerDOB = playerDOB;
        this.playerTeamName = playerTeamName;
        this.playerWeight = playerWeight;
        this.playerAdditionalInfo = playerAdditionalInfo;
        this.playerLicense = playerLicense;
        this.playerSubscriptions = new ArrayList<>();
    }


    @Override  //TODO sprawdzic ta metode
    public String getName() {
        return playerFirstName + " " + playerLastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Double.compare(player.getPlayerWeight(),
                getPlayerWeight()) == 0 && Objects.equals(getPlayerFirstName(),
                player.getPlayerFirstName()) && Objects.equals(getPlayerLastName(),
                player.getPlayerLastName()) && Objects.equals(getPlayerDOB(),
                player.getPlayerDOB()) && Objects.equals(getPlayerTeamName(),
                player.getPlayerTeamName()) && Objects.equals(getPlayerAdditionalInfo(),
                player.getPlayerAdditionalInfo()) && Objects.equals(getPlayerLicense(),
                player.getPlayerLicense()) && Objects.equals(getPlayerSubscriptions(),
                player.getPlayerSubscriptions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerFirstName(),
                getPlayerLastName(),
                getPlayerDOB(),
                getPlayerTeamName(),
                getPlayerWeight(),
                getPlayerAdditionalInfo(),
                getPlayerLicense(),
                getPlayerSubscriptions());
    }


    //TODO napisac metody
//    public static Player createWith(RegisterPlayerForm form) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        return new Player();
//    }

//    public void addSubscription(Subscription subscription){
//
//    }

//    public List<Subscription> getApprovedSubscriptions() {
//
//    }


}
