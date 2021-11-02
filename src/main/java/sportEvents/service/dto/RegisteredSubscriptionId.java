package sportEvents.service.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class RegisteredSubscriptionId {

    @NonNull
    UUID userId;
    UUID subscriptionId;

}
