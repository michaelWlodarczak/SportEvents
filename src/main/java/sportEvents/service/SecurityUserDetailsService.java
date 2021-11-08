package sportEvents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sportEvents.entity.User;
import sportEvents.entity.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserLogin(userLogin);
        //check if optional exist and use lambda to throw exception
        user.orElseThrow(()-> new UsernameNotFoundException("User not found !"));
        //map object to instance of new object
        return user.map(SecurityUserDetails::new).get();
    }
}
