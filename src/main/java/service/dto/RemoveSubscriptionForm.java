package service.dto;


import entity.Event;
import lombok.Value;

import java.util.UUID;

@Value
public class RemoveSubscriptionForm {


    //TODO zapytac o co chodzi ...
    UUID userId;
    Event event;


}