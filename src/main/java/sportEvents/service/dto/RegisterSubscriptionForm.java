package sportEvents.service.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class RegisterSubscriptionForm {

   UUID userId;
   boolean subscriptionPaymentDone;
   String subscriptionDate;
   boolean subscriptionApproved;
   @NonNull
   UUID eventId;
}
