package sportEvents.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sportEvents.entity.Event;
import sportEvents.entity.Organizer;
import sportEvents.entity.Player;
import sportEvents.entity.repositories.EventsRepository;
import sportEvents.entity.repositories.SubscriptionRepository;
import sportEvents.entity.repositories.UserRepository;
import sportEvents.service.dto.RegisterSubscriptionForm;
import sportEvents.service.dto.RemoveSubscriptionForm;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PlayerSubscriptionServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlayerSubscriptionService playerSubscriptionService;
    @Autowired
    private EventsRepository eventRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Test
    void shouldAddSubscriptionToPlayer(){
        final var user1 = new Player("123",
                "player1",
                "player@player.com",
                "PlayerCity",
                "PlayerStreet",
                "Poland",
                "00000",
                "PlayerName",
                "PlayerLastName",
                LocalDate.of(1990,1,1),
                "",
                0,
                "",
                "",
                "123123123");
        userRepository.save(user1);
        final var organizer1 = new Organizer("123",
                "player1",
                "player@player.com",
                "PlayerCity",
                "PlayerStreet",
                "Poland",
                "00000",
                "OrganizerName");
        userRepository.save(organizer1);

        Organizer organizer = (Organizer) userRepository.getById(organizer1.getUserId());
        Event event = new Event("Test",LocalDateTime.now(),10,0,organizer);
        Player player1 = (Player) userRepository.getById(user1.getUserId());
        eventRepository.save(event);
        final var subscriptionForm = new RegisterSubscriptionForm(
                player1.getUserId(),
                true,
                LocalDateTime.now().toString(),
                true,
                event.getEventId());
        final var addedSubscription = playerSubscriptionService.addSubscription(subscriptionForm);
        assertNotNull(addedSubscription);
        assertEquals(player1.getPlayerSubscriptions().size(),1);
        assertEquals(player1.getPlayerSubscriptions().get(0).getSubscriptionId(),addedSubscription.getSubscriptionId());

    }
    @Test
    void shouldAddSubscriptionToPlayerAndRemoveIt(){
        final var user1 = new Player("123",
                "player1",
                "player51@player.com",
                "PlayerCity",
                "PlayerStreet",
                "Poland",
                "00000",
                "PlayerName",
                "PlayerLastName",
                LocalDate.of(1990,1,1),
                "",
                0,
                "",
                "",
                "123123123");
        userRepository.save(user1);
        final var organizer1 = new Organizer("123",
                "player1",
                "player55@player.com",
                "PlayerCity",
                "PlayerStreet",
                "Poland",
                "00000",
                "OrganizerName");
        userRepository.save(organizer1);

        Organizer organizer = (Organizer) userRepository.getById(organizer1.getUserId());
        Player player1 = (Player) userRepository.getById(user1.getUserId());
        Event event = new Event("Test",LocalDateTime.now(),10,0,organizer);
        eventRepository.save(event);
        final var subscriptionForm = new RegisterSubscriptionForm(
                player1.getUserId(),
                true,
                LocalDateTime.now().toString(),
                true,
                event.getEventId());
        final var removeSubscriptionForm = new RemoveSubscriptionForm(
                player1.getUserId(),
                event.getEventId());
        final var addedSubscription = playerSubscriptionService.addSubscription(subscriptionForm);
        assertNotNull(addedSubscription);
        assertEquals(player1.getPlayerSubscriptions().size(),1);
        assertEquals(player1.getPlayerSubscriptions().get(0).getSubscriptionId(),addedSubscription.getSubscriptionId());
        playerSubscriptionService.removeSubscription(removeSubscriptionForm);
        assertEquals(player1.getPlayerSubscriptions().size(),0);
    }

}