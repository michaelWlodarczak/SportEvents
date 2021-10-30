package entity;

import lombok.*;
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
@Setter
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

    public static Player updatePlayer(RegisterPlayerForm form, Player player){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        player.setUserPassword(form.getUserPassword());

        player.setUserEmail(form.getUserEmail());
        player.setUserStreet(form.getUserStreet());
        player.setUserCity(form.getUserCity());
        player.setUserCountry(form.getUserCountry());
        player.setUserZipCode(form.getUserZipCode());
        player.setPlayerFirstName(form.getPlayerFirstName());
        player.setPlayerLastName(form.getPlayerLastName());
        player.setPlayerDOB(LocalDate.parse(form.getPlayerDOB(),formatter));
        player.setPlayerTeamName(form.getPlayerTeamName());
        player.setPlayerWeight(Double.parseDouble(form.getPlayerWeight()));
        player.setPlayerAdditionalInfo(form.getPlayerAdditionalInfo());
        player.setPlayerLicense(form.getPlayerLicense());
        player.setPlayerPhone(form.getPlayerPhone());
        return player;
    }

    public void addSubscription(Subscription subscription){
        if(subscription != null){
            if(!playerSubscriptions.contains(subscription)){
                playerSubscriptions.add(subscription);
            }else {
                throw new SubscriptionException("Subscription for this event already exist for this Player");
            }
        }
    }

    public void removeSubscription(Event event){
        if (event != null){
            Subscription subscription = playerSubscriptions.stream()
                    .filter(subEvent -> event.equals(subEvent.getEvent()))
                    .findFirst().orElse(null);
            if(playerSubscriptions.contains(subscription)){
                playerSubscriptions.remove(subscription);
            }else {
                throw new SubscriptionException("Subscription for this event not exist for this Player");
            }
        }
    }

    public List<Subscription> getApprovedSubscriptions() {
        return playerSubscriptions.stream().
                filter(subEvent -> subEvent.isSubscriptionApproved())
                .collect(Collectors.toList());
    }

    public PlayerView toPlayerView(){
        return new PlayerView(getUserId(),
                getName(),
                getUserEmail(),
                getUserType(),
                getPlayerSubscriptions().size(),
                isUserActive());
    }

    public PlayerDetails viewDetail(){
        return new PlayerDetails(getUserId(),
                getName(),
                getUserEmail(),
                getUserType(),
                getUserCity(),
                getUserStreet(),
                getUserCountry(),
                getUserZipCode(),
                getPlayerSubscriptions().stream().map(Subscription::toView).collect(Collectors.toList()),
                getPlayerFirstName(),
                getPlayerLastName(),
                getPlayerDOB().toString(),
                getPlayerTeamName(),
                String.valueOf(getPlayerWeight()),
                getPlayerAdditionalInfo(),
                getPlayerLicense(),
                getPlayerPhone());
    }
}
