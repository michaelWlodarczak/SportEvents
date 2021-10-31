package controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.EventQuery;
import service.dto.EventDetails;
import service.dto.EventView;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/events",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EventViewRestController {

    @NonNull
    private final EventQuery query;

    @GetMapping
        // GET -> /api/events
        //default mapping
    List<EventView> getEvents() {
        return query.eventList();
    }

    @GetMapping(value = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    EventDetails getEvent(@PathVariable UUID eventId) {
        return query.getEventDetails(eventId);
    }
}
