package sportEvents.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sportEvents.entity.*;
import sportEvents.entity.Organizer;
import sportEvents.entity.Player;
import sportEvents.entity.User;
import sportEvents.entity.enums.UserType;
import sportEvents.entity.repositories.UserRepository;
import sportEvents.service.dto.*;
import sportEvents.service.dto.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class UserQuery {

    @NonNull
    private final UserRepository userRepository;
    public List<UserView> listAllUsers(){

        List<UserView> collect = userRepository.findAll().stream()
                .map(User::toUserView)
                .collect(Collectors.toList());
        return collect;
    }

    public List<PlayerView> listPlayers(){

        List<PlayerView> collect = userRepository.getAllPlayers().stream()
                .map(Player::toPlayerView)
                .collect(Collectors.toList());
        return collect;
    }
    public PlayerDetails getPlayerDetails(UUID userId){
        return userRepository.findPlayerByUserId(userId).viewDetail();
    }

    public List<OrganizerView> listOrganizers(){
        List<OrganizerView> collect = userRepository.getAllOrganizers().stream()
                .filter(organizer -> organizer.getUserType().equals(UserType.ORGANIZER))
                .map(Organizer::toOrganizerView)
                .collect(Collectors.toList());
        return collect;
    }
    public OrganizerDetails getOrganizerDetails(UUID userId){
        return userRepository.getOrganizerByUserId(userId).viewDetail();
    }
}

