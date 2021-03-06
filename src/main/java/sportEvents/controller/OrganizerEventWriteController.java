package sportEvents.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sportEvents.service.OrganizerEventService;
import sportEvents.service.UserService;
import sportEvents.service.dto.*;
import sportEvents.service.dto.DeletedEventId;
import sportEvents.service.dto.RegisterEventForm;
import sportEvents.service.dto.RegisteredEventId;
import sportEvents.service.dto.RemoveEventForm;

import java.util.UUID;

@RestController
@RequestMapping("/api/organizers")
@RequiredArgsConstructor
public class OrganizerEventWriteController {
    @NonNull  private final UserService userService;
    @NonNull  private final OrganizerEventService organizerEventService;

    @GetMapping("/{userId}/event")
    String infoText(){
        return "AddEvent";
    }

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
    //TODO PUT **
}
