package sportEvents.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sportEvents.service.UserMaintenanceService;
import sportEvents.service.UserService;
import sportEvents.service.dto.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserWriteRestController {

    @NonNull
    UserService userService;
    @NonNull
    UserMaintenanceService userMaintenanceService;

    @PostMapping("/players")
    ResponseEntity<RegisteredUserId> registerUser(@RequestBody RegisterPlayerForm form){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerPlayer(form));
    }

    @PutMapping("/players/{userId}")
    ResponseEntity<RegisteredUserId> updatePlayer(@RequestBody RegisterPlayerForm form, @PathVariable UUID userId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updatePlayer(form,userId));
    }

    @PostMapping("/organizers")
    ResponseEntity<RegisteredUserId> registerOrganizer(@RequestBody RegisterOrganizerForm form){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerOrganizer(form));
    }

    @PutMapping("/organizers/{userId}")
    ResponseEntity<RegisteredUserId> updateOrganizer(@RequestBody RegisterOrganizerForm form, @PathVariable UUID userId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateOrganizer(form,userId));
    }

//    @PutMapping("/activate/{userId}")
//    ResponseEntity<RegisteredUserId> activateUser(@PathVariable UUID userId){
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(userMaintenanceService.activateUser(userId)); //TODO
//    }
//
//    @PutMapping("/deactivate/{userId}")
//    ResponseEntity<RegisteredUserId> deactivateUser(@PathVariable UUID userId){
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(userMaintenanceService.deactivateUser(userId)); //TODO
//    }
}
