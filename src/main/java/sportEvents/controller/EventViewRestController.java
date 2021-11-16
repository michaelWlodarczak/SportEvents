package sportEvents.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sportEvents.service.EventQuery;
import sportEvents.service.dto.EventDetails;
import sportEvents.service.dto.EventView;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/api/events",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EventViewRestController {

        @NonNull
        private final EventQuery query;
        @CrossOrigin
        @GetMapping
            // default mapping
        List<EventView> getEvents() {
            return query.listEvents();
        }
        @CrossOrigin
        @GetMapping(value="/{eventId}",produces = MediaType.APPLICATION_JSON_VALUE)
        EventDetails getEvent(@PathVariable UUID eventId){
            return query.getEventDetails(eventId);
        }
    }

