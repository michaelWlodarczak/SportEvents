package service;

import entity.Organizer;
import entity.Player;
import entity.enums.UserType;
import entity.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import service.dto.OrganizerDetails;
import service.dto.OrganizerView;
import service.dto.PlayerDetails;
import service.dto.PlayerView;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserQuery {

    @NonNull
    private final UserRepository userRepository;

    public List<PlayerView> listPlayers (){
        List<PlayerView> collect = userRepository.getAllPlayers().stream()
                .map(Player::toPlayerView) //TODO
                .collect(Collectors.toList());
        return collect;
    }

    public PlayerDetails getPlayerDetails(UUID userId){
        return userRepository.findPlayerByUserId(userId).viewDetail();  //TODO
    }

    public List<OrganizerView> listOrganizers(){
        List<OrganizerView> collect = userRepository.getAllOrganizers().stream()
                .filter(organizer -> organizer.getUserType().equals(UserType.ORGANIZER))
                .map(Organizer::toOrganizerView) //TODO
                .collect(Collectors.toList());
        return collect;
    }

    public OrganizerDetails getOrganizerDetails(UUID userId){
        return userRepository.getOrganizerByUserId(userId).viewDetail();  //TODO
    }
}
