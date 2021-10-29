package service.dto;

import entity.enums.UserType;
import lombok.Value;

import java.util.UUID;

@Value
public class PlayerView {
    UUID userId;
    String name;
    String email;
    UserType type;
    int subscriptionCount;
    boolean active;
}
