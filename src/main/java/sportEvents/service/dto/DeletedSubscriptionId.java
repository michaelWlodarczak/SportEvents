package sportEvents.service.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class DeletedSubscriptionId {

    @NonNull
    UUID userId;
    UUID subscriptionId;
}
