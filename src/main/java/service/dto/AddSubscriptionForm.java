package service.dto;

import entity.Event;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class AddSubscriptionForm {

    UUID userId;
    boolean subscriptionPaymentDone;
    LocalDateTime dateOfPayment;
    boolean subscriptionApproved;
    Event event;
}
