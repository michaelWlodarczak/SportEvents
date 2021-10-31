package controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PlayerSubscriptionService;
import service.UserService;
import service.dto.DeletedSubscriptionId;
import service.dto.RegisterSubscriptionForm;
import service.dto.RegisteredSubscriptionId;
import service.dto.RemoveSubscriptionForm;

import java.util.UUID;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerSubscriptionWriteController {

    @NonNull
    UserService userService;
    @NonNull
    PlayerSubscriptionService playerSubscriptionService;

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
                .body(playerSubscriptionService.removeSubscriptionRest(form,userId)); // TODO ?!
    }

    //TODO PUT ?!

}
