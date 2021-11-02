package sportEvents.service;

import sportEvents.entity.Organizer;
import sportEvents.entity.Player;
import sportEvents.entity.enums.UserType;
import sportEvents.entity.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sportEvents.service.dto.OrganizerDetails;
import sportEvents.service.dto.OrganizerView;
import sportEvents.service.dto.PlayerDetails;
import sportEvents.service.dto.PlayerView;

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
