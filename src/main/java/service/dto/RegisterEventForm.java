package service.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class RegisterEventForm {
    @NonNull
    UUID userId;
    @NonNull
    String eventTitle;
    @NonNull
    String eventDate;
    String eventPlayerLimit;
    String eventFee;
}
