package sportEvents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sportEvents.entity.repositories.UserRepository;

import java.util.UUID;

@Component("userSecurity")
@Transactional
public class UserSecurity {

    @Autowired
    UserRepository userRepository;

    public boolean hasUserId(Authentication authentication, UUID userId) {
        if (authentication.getPrincipal() instanceof UserDetails) {
            SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
            System.out.println(userRepository.getById(userDetails.getUserId()).getUserLogin());
            return userId.equals(userRepository.getById(userDetails.getUserId()).getUserId());
        }
        return false;
    }
}
