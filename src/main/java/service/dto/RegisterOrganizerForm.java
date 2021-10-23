package service.dto;

import lombok.NonNull;

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
