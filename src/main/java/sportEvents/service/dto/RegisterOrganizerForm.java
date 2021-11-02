package sportEvents.service.dto;

import lombok.NonNull;
import lombok.Value;

@Value
public class RegisterOrganizerForm {

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
    String organizerName;


}
