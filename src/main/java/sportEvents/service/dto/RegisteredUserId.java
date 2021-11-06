package sportEvents.service.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value  //adnotacja zalatwia gettery, settery, equals i hashcode
public class RegisteredUserId {
    @NonNull
    UUID userId;
}
