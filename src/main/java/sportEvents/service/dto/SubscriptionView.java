package sportEvents.service.dto;

import lombok.Getter;
import lombok.Value;

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
