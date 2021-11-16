package sportEvents.service.dto;

import lombok.Getter;
import lombok.Value;
import sportEvents.entity.enums.UserType;

import java.util.List;
import java.util.UUID;
@Getter
@Value
public class OrganizerDetails {
   UUID userId;
   String organizerName;
   String userEmail;
   UserType userType;
   String userCity;
   String userStreet;
   String userCountry;
   String userZipCode;
   List<EventView> organizerEvents;
}
