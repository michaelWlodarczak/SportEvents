package sportEvents.service.dto;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class AddEventForm {

    @NonNull
    UUID userId;
    String eventTitle;
    LocalDateTime eventDate;
    int eventPlayerLimit;
    double eventFee;

}
