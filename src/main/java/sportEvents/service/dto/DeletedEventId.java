package sportEvents.service.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class DeletedEventId {
    @NonNull
    UUID userId;
    UUID eventId;
}
