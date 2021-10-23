package service.dto;

import entity.Subscription;
import entity.UserType;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class PlayerDetails {

    UUID userId;
    String name;
    String email;
    UserType userType;
    List<Subscription> playerSubscriptions;
    String userStreet;
    String userCity;
    String userCountry;
    String userZipCode;
    String playerFirstName;
    String playerLastName;
    String playerDOB;
    String playerTeamName;
    String playerWeight;
    String playerAdditionalInfo;
    String playerLicense;
    String playerPhone;

}
