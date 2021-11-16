package sportEvents.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sportEvents.entity.ConfirmationToken;
import sportEvents.entity.User;
import sportEvents.entity.repositories.ConfirmationTokenRepository;
import sportEvents.entity.repositories.UserRepository;
import sportEvents.service.dto.RegisterOrganizerForm;
import sportEvents.service.dto.RegisterPlayerForm;
import sportEvents.service.dto.RegisteredUserId;
import sportEvents.service.exception.EmailAlreadyExistException;
import sportEvents.service.exception.TokenValidationException;

@Service
@Transactional
@NoArgsConstructor
public class UserRegisterService  {
    @Value("${server.url}")
    private String serverUrl;
    @Value("${server.port}")
    String serverPort;
    @Value("${email.from}")
    String emailFrom;


    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    EmailSenderService emailSenderService;


    public void sendVerificationEmail(User user, ConfirmationToken confirmationToken) {
        String text = "To confirm your account, please click here : "
                + serverUrl+":"+serverPort+"/api/register/" + confirmationToken.getConfirmationToken();

     /*   SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUserEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom(emailFrom);
        mailMessage.setText(text);
        emailSenderService.sendEmail(mailMessage);*/
        System.out.println(text);
    }

    public RegisteredUserId registerPlayerWithEmailConfirmation(@NonNull RegisterPlayerForm form) throws EmailAlreadyExistException {
        RegisteredUserId registeredUserId = userService.registerPlayer(form);
        if (registeredUserId != null) {
            User user = userRepository.getById(registeredUserId.getUserId());
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            sendVerificationEmail(user, confirmationToken);

        }
        return registeredUserId;
    }

    public RegisteredUserId registerOrganizerWithEmailConfirmation(@NonNull RegisterOrganizerForm form) throws EmailAlreadyExistException {
        RegisteredUserId registeredUserId = userService.registerOrganizer(form);
        if (registeredUserId != null) {
            User user = userRepository.getById(registeredUserId.getUserId());
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            sendVerificationEmail(user, confirmationToken);

        }
        return registeredUserId;
    }

    public RegisteredUserId verifyUser(@NonNull String tokenId) throws TokenValidationException {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(tokenId);
        if (token != null) {
            User user = userRepository.getById(token.getUser().getUserId());
            user.setUserActive(true);
            userRepository.save(user);
            return new RegisteredUserId(user.getUserId());
        } else {
            throw new TokenValidationException("Token not Valid !");
        }
    }

}
