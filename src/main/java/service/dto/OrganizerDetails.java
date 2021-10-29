package service.dto;

import entity.enums.UserType;
import lombok.Getter;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Getter
@Value
public class OrganizerDetails {
    UUID userId;
    String organizerName;
    String userEmail;
    UserType userType;
    String userStreet;
    String userCity;
    String userCountry;
    String userZipCode;
    List<EventView> organizerEvents;
}
