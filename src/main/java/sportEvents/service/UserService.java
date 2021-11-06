package sportEvents.service;

import sportEvents.entity.Organizer;
import sportEvents.entity.Player;
import sportEvents.entity.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import sportEvents.service.dto.RegisterOrganizerForm;
import sportEvents.service.dto.RegisterPlayerForm;
import sportEvents.service.dto.RegisteredUserId;
import sportEvents.service.exception.EmailAlreadyExistException;

import javax.transaction.Transactional;

import java.util.UUID;

@Service // TODO sprawdzic adnotacje
@Transactional // TODO sprawdzic adnotacje
@RequiredArgsConstructor // TODO sprawdzic adnotacje
public class UserService {
    @NonNull
    private final UserRepository userRepository;

    public RegisteredUserId registerPlayer(@NonNull RegisterPlayerForm form) throws EmailAlreadyExistException {
        if (userRepository.emailExists(form.getUserEmail()))
        {
            throw new EmailAlreadyExistException("Account with email exist: "+form.getUserEmail());
        }
        Player player = Player.createWith(form);
        userRepository.save(player);
        return new RegisteredUserId(player.getUserId());
    }
    public RegisteredUserId updatePlayer(@NonNull RegisterPlayerForm form, UUID userId) {
        Player player = userRepository.getPlayerByUserId(userId);
        Player.updatePlayer(form, player);
        userRepository.save(player);
        return new RegisteredUserId(player.getUserId());
    }

    public RegisteredUserId registerOrganizer(@NonNull RegisterOrganizerForm form) throws EmailAlreadyExistException {
        if (userRepository.emailExists(form.getUserEmail()))
        {
            throw new EmailAlreadyExistException("Account with email exist: "+form.getUserEmail());
        }
        Organizer organizer = Organizer.createWith(form);
        userRepository.save(organizer);
        return new RegisteredUserId(organizer.getUserId());
    }

    public RegisteredUserId updateOrganizer(@NonNull RegisterOrganizerForm form, UUID userId) {
        Organizer organizer = userRepository.getOrganizerByUserId(userId);
        Organizer.updateOrganizer(form, organizer);
        userRepository.save(organizer);
        return new RegisteredUserId(organizer.getUserId());
    }
}