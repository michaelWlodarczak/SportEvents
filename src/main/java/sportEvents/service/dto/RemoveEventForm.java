package sportEvents.service.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class RemoveEventForm {
    UUID userId;
    UUID eventId;
}
