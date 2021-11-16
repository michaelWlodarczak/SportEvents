package sportEvents.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sportEvents.service.PlayerSubscriptionService;
import sportEvents.service.UserService;
import sportEvents.service.dto.DeletedSubscriptionId;
import sportEvents.service.dto.RegisterSubscriptionForm;
import sportEvents.service.dto.RegisteredSubscriptionId;
import sportEvents.service.dto.RemoveSubscriptionForm;

import java.util.UUID;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerSubscriptionWriteController {
    @NonNull  private final UserService userService;
    @NonNull  private final  PlayerSubscriptionService playerSubscriptionService;

    @PostMapping("/{userId}/subscriptions")
    ResponseEntity<RegisteredSubscriptionId> registerSubscription(@RequestBody RegisterSubscriptionForm form, @PathVariable UUID userId){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playerSubscriptionService.addSubscriptionRest(form,userId));
    }
    @DeleteMapping("/{userId}/subscriptions")
    ResponseEntity<DeletedSubscriptionId> removeSubscription(@RequestBody RemoveSubscriptionForm form, @PathVariable UUID userId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(playerSubscriptionService.removeSubscriptionRest(form,userId));
    }
    //TODO PUT **
}
