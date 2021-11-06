package sportEvents.service.dto;

import lombok.NonNull;
import lombok.Value;

@Value
public class RegisterPlayerForm {

    @NonNull
    String userLogin;
    @NonNull
    String userPassword;
    @NonNull
    String userEmail;
    String userStreet;
    String userCity;
    String userCountry;
    String userZipCode;
    @NonNull
    String playerFirstName;
    @NonNull
    String playerLastName;
    @NonNull
    String playerDOB;
    String playerTeamName;
    String playerWeight;
    String playerAdditionalInfo;
    String playerLicence;
    @NonNull
    String playerPhone;
}
