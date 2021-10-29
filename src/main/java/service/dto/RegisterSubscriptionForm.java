package service.dto;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class RegisterSubscriptionForm {
    @NonNull
    UUID userId;
    boolean subscriptionPaymentDone;
    LocalDateTime subscriptionDate;
    boolean subscriptionApproved;
    @NonNull
    UUID eventId;
}
