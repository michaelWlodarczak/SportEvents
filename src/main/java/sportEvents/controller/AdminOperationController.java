package sportEvents.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sportEvents.service.*;
import sportEvents.service.dto.*;
import sportEvents.service.*;
import sportEvents.service.dto.EventView;
import sportEvents.service.dto.MaintenanceUserId;
import sportEvents.service.dto.UserView;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminOperationController {
    @NonNull
    private final UserQuery userQuery;
    @NonNull
    private final UserService userService;
    @NonNull
    private final PlayerSubscriptionService playerSubscriptionService;
    @NonNull
    private final OrganizerEventService organizerEventService;
    @NonNull
    private final UserMaintenanceService userMaintenanceService;
    @NonNull
    private final EventQuery eventQuery;

    @GetMapping("/users")
    List<UserView> getAllUsers() {
        return userQuery.listAllUsers();
    }
    @GetMapping("/events")
    List<EventView> getEvents() {
        return eventQuery.listEvents();
    }

    @PostMapping("/users/deactivate/{userId}")
    ResponseEntity<MaintenanceUserId> deactivateUser(@PathVariable UUID userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userMaintenanceService.deactivateUser(userId));
    }

    @PostMapping("/users/activate/{userId}")
    ResponseEntity<MaintenanceUserId> activateUser(@PathVariable UUID userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userMaintenanceService.activateUser(userId));
    }
}
