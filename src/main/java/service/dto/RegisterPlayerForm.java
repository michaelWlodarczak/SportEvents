package service.dto;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;

@Value
public class RegisterPlayerForm {

    @NonNull //TODO przypomniec sobie tą adnotacje
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
    LocalDate playerDOB;
    String playerTeamName;
    double playerWeight;
    String playerAdditionalInfo;
    String playerLicense;
}
