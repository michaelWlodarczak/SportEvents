package sportEvents.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sportEvents.service.UserMaintenanceService;
import sportEvents.service.UserRegisterService;
import sportEvents.service.dto.RegisterOrganizerForm;
import sportEvents.service.dto.RegisterPlayerForm;
import sportEvents.service.dto.RegisteredUserId;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationServiceController {

    @NonNull
    private final UserRegisterService userRegisterService;
    @NonNull
    private final UserMaintenanceService userMaintenanceService;

    @PostMapping("/register/player")
    ResponseEntity<RegisteredUserId> registerUser(@RequestBody RegisterPlayerForm form) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userRegisterService.registerPlayerWithEmailConfirmation(form));
    }
    @PostMapping("/register/organizer")
    ResponseEntity<RegisteredUserId> registerUser(@RequestBody RegisterOrganizerForm form) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userRegisterService.registerOrganizerWithEmailConfirmation(form));
    }

    @GetMapping("/register/{tokenId}")
    ResponseEntity<RegisteredUserId> verifyUser(@PathVariable String tokenId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userRegisterService.verifyUser(tokenId));
    }


}
