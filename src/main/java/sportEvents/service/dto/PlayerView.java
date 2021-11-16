package sportEvents.service.dto;

import lombok.Value;
import sportEvents.entity.enums.UserType;

import java.util.UUID;

@Value
public class PlayerView {
    UUID userId;
    String name;
    String email;
    UserType type;
    int subscriptionsCount;
    boolean active;
}
