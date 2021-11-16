package sportEvents.service.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class DeletedEventId {
    UUID userId;
    @NonNull
    UUID eventId;
}
