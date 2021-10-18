package service;


import entity.Player;
import entity.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRegistrationService {
    @NonNull
    private final UserRepository userRepository;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public RegisteredUserId registerPlayer(@NonNull RegisterPlayerForm form){
            Player player = Player.createWith(form);
            return new RegisteredUserId(player.getUserId());
        }



}
