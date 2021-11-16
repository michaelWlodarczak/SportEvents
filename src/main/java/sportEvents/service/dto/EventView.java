package sportEvents.service.dto;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class EventView {
    UUID eventId;
    String Title;
    LocalDateTime eventDate;
    int eventPlayerLimit;
    double eventFee;
    int subscriptionsCount;
}
