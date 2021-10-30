package service;


import entity.Organizer;
import entity.Player;
import entity.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import service.dto.RegisterOrganizerForm;
import service.dto.RegisterPlayerForm;
import service.dto.RegisteredUserId;
import service.exception.EmailAlreadyExistException;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service // TODO sprawdzic adnotacje
@Transactional // TODO sprawdzic adnotacje
@RequiredArgsConstructor // TODO sprawdzic adnotacje
public class UserService {
    @NonNull
    private final UserRepository userRepository;

        public RegisteredUserId registerPlayer(@NonNull RegisterPlayerForm form)throws EmailAlreadyExistException {
            if(userRepository.emailExists(form.getUserEmail())){
                throw new EmailAlreadyExistException("Account with email exists: " + form.getUserEmail());
            }
            Player player = Player.createWith(form);
            userRepository.save(player);
            return new RegisteredUserId(player.getUserId());
        }


        public RegisteredUserId updatePlayer(@NonNull RegisterPlayerForm form, UUID userId){
            Player player = userRepository.getPlayerByUserId(userId);
            Player.updatePlayer(form, player); //TODO
            userRepository.save(player);
            return new RegisteredUserId(player.getUserId());
        }

        public RegisteredUserId registerOrganizer(@NonNull RegisterOrganizerForm form) throws EmailAlreadyExistException{
            if (userRepository.emailExists(form.getUserEmail())){
                throw new EmailAlreadyExistException("Account with email exists: " + form.getUserEmail());
            }
            Organizer organizer = Organizer.createWith(form);
            userRepository.save(organizer);
            return new RegisteredUserId(organizer.getUserId());
        }

        public RegisteredUserId updateOrganizer(@NonNull RegisterOrganizerForm form, UUID userId){
            Organizer organizer = userRepository.getOrganizerByUserId(userId);
            Organizer.updatOrganizer(form, organizer); //TODO
            userRepository.save(organizer);
            return new RegisteredUserId(organizer.getUserId());
        }
}
