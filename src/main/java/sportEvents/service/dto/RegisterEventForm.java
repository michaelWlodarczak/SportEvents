package sportEvents.service.dto;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class RegisterEventForm {
    UUID userId;
    @NonNull
    String eventTitle;
    @NonNull
    String eventDate;
    String eventPlayerLimit;
    String eventFee;
}
