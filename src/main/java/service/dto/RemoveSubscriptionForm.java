package service.dto;


import entity.Event;
import lombok.Value;

import java.util.UUID;

@Value
public class RemoveSubscriptionForm {

    UUID userId;
    Event event;

}
