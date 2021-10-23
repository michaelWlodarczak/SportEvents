package service.dto;

import entity.Event;
import lombok.Value;

import java.util.UUID;

@Value
public class RemoveEventForm {

    UUID userId;
    Event event;

}
