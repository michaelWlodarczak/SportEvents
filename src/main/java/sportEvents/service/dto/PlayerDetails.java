package sportEvents.service.dto;

import sportEvents.entity.enums.UserType;
import lombok.Getter;
import lombok.Value;

import java.util.List;
import java.util.UUID;
@Getter
@Value
public class PlayerDetails {
    UUID userId;
    String name;
    String email;
    UserType type;
    String userStreet;
    String userCity;
    String userCountry;
    String userZipCode;
    List<SubscriptionView> playerSubscriptions;
    String playerFirstName;
    String playerLastName;
    String playerDOB;
    String playerTeamName;
    String playerWeight;
    String playerAdditionalInfo;
    String playerLicense;
    String playerPhone;
}
