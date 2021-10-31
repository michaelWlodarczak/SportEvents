package controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserQuery;
import service.dto.OrganizerDetails;
import service.dto.OrganizerView;
import service.dto.PlayerDetails;
import service.dto.PlayerView;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserViewRestContoller {

    @NonNull
    private final UserQuery query;

    @GetMapping(value = "/players")
    List<PlayerView> getPlayers() {
        return query.listPlayers();
    }

    @GetMapping(value = "players/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    PlayerDetails getPlayer(@PathVariable UUID userId) {
        return query.getPlayerDetails(userId);
    }

    @GetMapping(value = "/organizers")
    List<OrganizerView> getOrganizers(){
        return query.listOrganizers();
    }

    @GetMapping(value = "organizers/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    OrganizerDetails getOrganizerDetails(@PathVariable UUID userId){
            return  query.getOrganizerDetails(userId);
        }
    }