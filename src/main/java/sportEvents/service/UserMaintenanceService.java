package sportEvents.service;

import org.springframework.transaction.annotation.Transactional;
import sportEvents.entity.User;
import sportEvents.entity.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserMaintenanceService {
    @NonNull
    private final UserRepository userRepository;

    public void deactivateUser(UUID userId){
        User user = userRepository.getById(userId);
        user.setUserActive(false);
        userRepository.save(user);
    }
    public void activateUser(UUID userId){
        User user = userRepository.getById(userId);
        user.setUserActive(true);
        userRepository.save(user);
    }
}
