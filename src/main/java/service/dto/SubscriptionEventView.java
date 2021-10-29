package service.dto;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class SubscriptionEventView {
    String subscriptionId;
    String subscriptionPaymentDone;
    String subscriptionDate;
    String subscriptionApproved;
    String eventTitle;
    String eventDate;
    String playerId;
    String playerEmail;
}
