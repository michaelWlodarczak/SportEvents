package service;

import entity.Player;
import entity.Subscription;
import entity.UserType;
import entity.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.dto.AddSubscriptionForm;
import service.dto.RegisteredSubscription;
import service.dto.RemoveSubscriptionForm;
import service.exception.SubscriptionException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerSubscriptionService {

    @NonNull
    private final UserRepository userRepository;

    public RegisteredSubscription addSubscription(@NonNull AddSubscriptionForm form){
        if (userRepository.getById(form.getUserId()) == null) {
            throw new SubscriptionException("");
        }
        if (!(userRepository.getById(form.getUserId()).getUserType().equals(UserType.PLAYER))) {
            throw new SubscriptionException("The user is not a Player");
        }
        Player player = userRepository.getPlayerByUserId(form.getUserId());
        Subscription subscription = new Subscription(
                form.isSubscriptionPaymentDone(),
                LocalDateTime.now(),
                form.isSubscriptionApproved(),
                form.getEvent());
        player.addSubscription(subscription);
        userRepository.save(player);
        return new RegisteredSubscription(player.getUserId(),subscription.getSubscriptionId());
    }

    //TODO dokonczyc tą metode
//    public RegisteredSubscription removeSubscription(@NonNull RemoveSubscriptionForm form){
//        Player player = userRepository.getPlayerByUserId(form.getUserId());
//        player.removeSubscription(form.getEvent());
//        userRepository.save(player);
//    }
}
