package service.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class RegisteredEvent {

    @NonNull
    UUID userId;
    UUID eventId;

}
