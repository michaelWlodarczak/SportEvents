package entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import service.dto.PlayerDetails;
import service.dto.PlayerView;
import service.dto.RegisterPlayerForm;
import service.exception.SubscriptionException;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("PLAYER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Player extends User {

    private String playerFirstName;
    private String playerLastName;
    private LocalDate playerDOB;
    private String playerTeamName;
    private double playerWeight;
    private String playerAdditionalInfo;
    private String playerLicense;
    private String playerPhone;


    @OneToMany(cascade = CascadeType.ALL,mappedBy="player",orphanRemoval = true, fetch = FetchType.EAGER) // TODO sprawdzic to
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
                  @NonNull String playerPhone) {
        super(userLogin, userPassword, userEmail, userStreet, userCity, userCountry, userZipCode);
        this.playerFirstName = playerFirstName;
        this.playerLastName = playerLastName;
        this.playerDOB = playerDOB;
        this.playerTeamName = playerTeamName;
        this.playerWeight = playerWeight;
        this.playerAdditionalInfo = playerAdditionalInfo;
        this.playerLicense = playerLicense;
        this.playerPhone = playerPhone;
        this.playerSubscriptions = new ArrayList<>();
    }


    @Override
    public String getName() {
        return playerFirstName + " " + playerLastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return playerFirstName.equals(player.playerFirstName)
                && playerLastName.equals(player.playerLastName)
                && playerDOB.equals(player.playerDOB)
                && playerPhone.equals(player.playerPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), playerFirstName, playerLastName, playerDOB, playerPhone);
    }

    public static Player createWith(RegisterPlayerForm form) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new Player(form.getUserLogin(),
                form.getUserPassword(),
                form.getUserEmail(),
                form.getUserStreet(),
                form.getUserCity(),
                form.getUserCountry(),
                form.getUserZipCode(),
                form.getPlayerFirstName(),
                form.getPlayerLastName(),
                LocalDate.parse(form.getPlayerDOB(),formatter),
                form.getPlayerTeamName(),
                Double.valueOf(form.getPlayerWeight()),
                form.getPlayerAdditionalInfo(),
                form.getPlayerLicense(),
                form.getPlayerPhone());
    }

    public void addSubscription(Subscription subscription){
        if(subscription != null){
            if(!playerSubscriptions.contains(subscription)){
                playerSubscriptions.add(subscription);
            }else {
                throw new SubscriptionException("Subscription for this player already exists");
            }
        }
    }

    public void removeSubscription(Subscription subscription){
        if(subscription != null){
            if (playerSubscriptions.contains(subscription)){
                playerSubscriptions.remove(subscription);
            }else {
                throw new SubscriptionException("Subscription for this Event does not exist for this Player");
            }
        }
    }

    public List<Subscription> getApprovedSubscriptions() {
        return playerSubscriptions.stream().filter(subscription -> subscription.isSubscriptionApproved())
                .collect(Collectors.toList());
    }
//TODO zaczac od tego
//    public PlayerView toView(){
//        return new PlayerView(getUserId(),
//                getName(),
//                getUserEmail(),
//                getUserType());
//    }

    //TODO poprawic
//    public PlayerDetails toDetails(){
//        return new PlayerDetails(getUserId(),
//                getName(),
//                getUserEmail(),
//                getUserType(),
//                getPlayerSubscriptions(),
//                getUserStreet(),
//                getUserCity(),
//                getUserCountry(),
//                getUserZipCode(),
//                getPlayerFirstName(),
//                getPlayerLastName(),
//                getPlayerDOB().toString(),
//                getPlayerTeamName(),
//                String.valueOf(getPlayerWeight()),
//                getPlayerAdditionalInfo(),
//                getPlayerLicense(),
//                getPlayerPhone());
//    }


}
