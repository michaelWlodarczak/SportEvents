package sportEvents.service;

import sportEvents.entity.Organizer;
import sportEvents.entity.Player;
import sportEvents.entity.User;
import sportEvents.entity.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import sportEvents.service.dto.RegisterOrganizerForm;
import sportEvents.service.dto.RegisterPlayerForm;
import sportEvents.service.dto.RegisteredUserId;
import sportEvents.service.exception.EmailAlreadyExistException;
import sportEvents.service.exception.UserNotExistException;

import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@Service // TODO sprawdzic adnotacje
@Transactional // TODO sprawdzic adnotacje
@RequiredArgsConstructor
public class UserService {
    @NonNull
    private final UserRepository userRepository;

    public RegisteredUserId updateUserRoles(UUID userId, String userRole) throws UserNotExistException {
        User user = userRepository.getById(userId);
        if (user != null) {
            List<String> roles = List.of(userRole.split(","));
            user.setUserRoles(roles);
            //userRepository.save(user);
            return new RegisteredUserId(user.getUserId());
        } else {
            throw new UserNotExistException("User not exists");
        }
    }

    public RegisteredUserId registerPlayer(@NonNull RegisterPlayerForm form) throws EmailAlreadyExistException {
        if (userRepository.emailExists(form.getUserEmail(), null) || userRepository.loginExists(form.getUserLogin(), null)) {
            throw new EmailAlreadyExistException("Account with email or name exists");
        }
        Player player = Player.createWith(form);
        userRepository.save(player);
        return new RegisteredUserId(player.getUserId());
    }

    public RegisteredUserId updatePlayer(@NonNull RegisterPlayerForm form, UUID userId) {
        if (userRepository.emailExists(form.getUserEmail(), userId) || userRepository.loginExists(form.getUserLogin(), userId)) {
            throw new EmailAlreadyExistException("Account with email or name exists");
        }
        Player player = userRepository.getPlayerByUserId(userId);
        Player.updatePlayer(form, player);
        userRepository.save(player);
        return new RegisteredUserId(player.getUserId());
    }

    public RegisteredUserId registerOrganizer(@NonNull RegisterOrganizerForm form) throws EmailAlreadyExistException {
        if (userRepository.emailExists(form.getUserEmail(), null) || userRepository.loginExists(form.getUserLogin(), null)) {
            throw new EmailAlreadyExistException("Account with email or name exists");
        }
        Organizer organizer = Organizer.createWith(form);
        userRepository.save(organizer);
        return new RegisteredUserId(organizer.getUserId());
    }

    public RegisteredUserId updateOrganizer(@NonNull RegisterOrganizerForm form, UUID userId) {
        if (userRepository.emailExists(form.getUserEmail(), userId) || userRepository.loginExists(form.getUserLogin(), userId)) {
            throw new EmailAlreadyExistException("Account with email or name exists");
        }
        Organizer organizer = userRepository.getOrganizerByUserId(userId);
        Organizer.updateOrganizer(form, organizer);
        userRepository.save(organizer);
        return new RegisteredUserId(organizer.getUserId());
    }
}
