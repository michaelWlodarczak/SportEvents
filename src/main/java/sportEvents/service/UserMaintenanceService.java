package sportEvents.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sportEvents.entity.User;
import sportEvents.entity.repositories.UserRepository;
import sportEvents.service.dto.MaintenanceUserId;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserMaintenanceService {
    @NonNull
    private final UserRepository userRepository;

    public MaintenanceUserId deactivateUser(UUID userId){
        User user = userRepository.getById(userId);
        user.setUserActive(false);
        userRepository.save(user);
        return new MaintenanceUserId(userId);
    }
    public MaintenanceUserId activateUser(UUID userId){
        User user = userRepository.getById(userId);
        user.setUserActive(true);
        userRepository.save(user);
        return new MaintenanceUserId(userId);
    }
}
