package sportEvents.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sportEvents.entity.repositories.ConfirmationTokenRepository;
import sportEvents.entity.repositories.UserRepository;

@Service
@Transactional
@NoArgsConstructor
public class UserRegisterService {

    @Value("http://localhost")
    private String serverUrl;
    @Value("${server.port}")
    String serverPort;
    @Value("${email.from}")
    String emailFrom;

    /*
    Z wykorzystaniem adnotacji @Value można wyczytywać wartości zapisane w pliku konfiguracyjnym.
    Domyślny plik konfiguracyjny dla SpringBoot jest automatycznie tworzony i nazywa się
    application.properties.

    This annotation can be used for injecting values into fields in Spring-managed beans,
    and it can be applied at the field or constructor/method parameter level.
     */

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    EmailSenderService emailSenderService;



}
