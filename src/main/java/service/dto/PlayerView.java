package service.dto;

import entity.UserType;
import lombok.Value;

import java.util.UUID;

@Value
public class PlayerView {
    UUID userId;
    String name;
    String email;
    UserType userType;

}
