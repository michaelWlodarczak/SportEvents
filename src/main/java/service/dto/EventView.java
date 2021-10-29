package service.dto;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class EventView {
    UUID eventId;
    String title;
    LocalDateTime eventDate;
    int eventPlayerLimit;
    double eventFee;
    int subscriptionCount;
}
