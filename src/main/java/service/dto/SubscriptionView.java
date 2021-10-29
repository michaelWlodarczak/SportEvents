package service.dto;

import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Value
public class SubscriptionView {
    String subscriptionId;
    String subscriptionPaymentDone;
    String dateOfPayment;
    String subscriptionApproved;
    String eventTitle;
    String eventDate;
    String eventId;
}
