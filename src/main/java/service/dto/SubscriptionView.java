package service.dto;

import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Value
public class SubscriptionView {

    UUID subscriptionId;
    boolean subscriptionPaymentDone;
    LocalDateTime dateOfPayment;
    boolean subscriptionApproved;
    String eventTitle;
    String eventDate;
    String eventId;


}
