package sportEvents.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sportEvents.service.UserMaintenanceService;
import sportEvents.service.UserService;
import sportEvents.service.dto.*;
import sportEvents.service.dto.RegisterOrganizerForm;
import sportEvents.service.dto.RegisterPlayerForm;
import sportEvents.service.dto.RegisteredUserId;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserWriteRestController {
    @NonNull private final UserService userService;
    @NonNull private final UserMaintenanceService userMaintenanceService;
    //TODO check patch mapping OR DO WITH PUT FOR ACTIVATE / DEACTIVATE USERS
    /* User Registration */


    @PostMapping("/players")
    //@PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<RegisteredUserId> registerUser(@RequestBody RegisterPlayerForm form){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerPlayer(form));
    }
    @PutMapping("/players/{userId}")
    //@PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<RegisteredUserId> updatePlayer(@RequestBody RegisterPlayerForm form, @PathVariable UUID userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updatePlayer(form,userId));
    }


    @PostMapping("/organizers")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<RegisteredUserId> registerOrganizer(@RequestBody RegisterOrganizerForm form){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerOrganizer(form));
    }
    @PutMapping("/organizers/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<RegisteredUserId> updateOrganizer(@RequestBody RegisterOrganizerForm form, @PathVariable UUID userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateOrganizer(form,userId));
    }



}
