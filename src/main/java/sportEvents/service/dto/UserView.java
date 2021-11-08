package sportEvents.service.dto;

import lombok.Value;
import sportEvents.entity.enums.UserType;

import java.util.UUID;

@Value
public class UserView {
    UUID userId;
    String name;
    String email;
    UserType type;
    boolean active;
}
