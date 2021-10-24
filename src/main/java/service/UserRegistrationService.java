package service;


import entity.Player;
import entity.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import service.dto.RegisterOrganizerForm;
import service.dto.RegisterPlayerForm;
import service.dto.RegisteredUserId;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;

@Service // TODO sprawdzic adnotacje
@Transactional // TODO sprawdzic adnotacje
@RequiredArgsConstructor // TODO sprawdzic adnotacje
public class UserRegistrationService {
    @NonNull
    private final UserRepository userRepository;

//        public RegisteredUserId registerPlayer(@NonNull RegisterPlayerForm form){
//
//        }
//
//        public RegisteredUserId registerOrganizer(@NonNull RegisterOrganizerForm form){
//
//        }




}
