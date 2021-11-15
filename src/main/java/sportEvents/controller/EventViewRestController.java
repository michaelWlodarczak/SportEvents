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

    @GetMapping
        // default mapping
        // GET -> /api/events
    List<EventView> getEvents() {
        return query.listEvents();
    }

    @CrossOrigin
    @GetMapping(value="/{eventId}",produces = MediaType.APPLICATION_JSON_VALUE)
    EventDetails getEvent(@PathVariable UUID eventId){
        return query.getEventDetails(eventId);
    }

    /*
    @CrossOrigin - Cross-Origin Resource Sharing (CORS) is a security concept
    that allows restricting the resources implemented in web browsers.
    It prevents the JavaScript code producing or consuming the requests against different origin.

    We need to set the origins for RESTful web service by using @CrossOrigin
    annotation for the controller method.
    This @CrossOrigin annotation supports specific REST API, and not for the entire application.
     */

    /*
    Annotation for mapping HTTP GET requests onto specific handler methods.
    Specifically, @GetMapping is a composed annotation that acts as a shortcut for
    @RequestMapping(method = RequestMethod. GET) .
     */
}