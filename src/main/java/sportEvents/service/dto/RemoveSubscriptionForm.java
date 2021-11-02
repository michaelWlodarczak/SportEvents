package sportEvents.service.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class RemoveSubscriptionForm {
    UUID userId;
    UUID eventId;
}
