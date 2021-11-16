package sportEvents.service.dto;

import lombok.Getter;
import lombok.Value;
import sportEvents.entity.enums.UserType;

import java.util.List;
import java.util.UUID;
@Getter
@Value
public class PlayerDetails {
    UUID userId;
    String name;
    String email;
    UserType type;
    String userCity;
    String userStreet;
    String userCountry;
    String userZipCode;
    List<SubscriptionView> playerSubscriptions;
    String playerFirstName;
    String playerLastName;
    String playerDOB;
    String playerTeamName;
    String playerWeight;
    String playerAdditionalInfo;
    String playerLicence;
    String playerPhone;
}
