package controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.OrganizerEventService;
import service.UserService;
import service.dto.DeletedEventId;
import service.dto.RegisterEventForm;
import service.dto.RegisteredEventId;
import service.dto.RemoveEventForm;

import java.util.UUID;

@RestController
@RequestMapping("/api/organizers")
@RequiredArgsConstructor
public class OrganizerEventWriteController {

    @NonNull
    UserService userService;
    @NonNull OrganizerEventService organizerEventService;

    @PostMapping("/{userId}/event")
    ResponseEntity<RegisteredEventId> registerEvent(@RequestBody RegisterEventForm form, @PathVariable UUID userId){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(organizerEventService.addEventRest(form,userId));
    }

    @DeleteMapping("/{userId}/event")
    ResponseEntity<DeletedEventId> removeSubscription(@RequestBody RemoveEventForm form, @PathVariable UUID userId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organizerEventService.removeEventRest(form,userId));

    }

    //  TODO PUT ?!
}
