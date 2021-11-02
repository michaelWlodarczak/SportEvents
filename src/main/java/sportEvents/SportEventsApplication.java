package sportEvents;

import sportEvents.entity.repositories.EventsRepository;
import sportEvents.entity.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import sportEvents.service.PlayerSubscriptionService;
import sportEvents.service.UserService;
import sportEvents.service.dto.RegisterEventForm;
import sportEvents.service.dto.RegisterOrganizerForm;
import sportEvents.service.dto.RegisterPlayerForm;
import sportEvents.service.dto.RegisterSubscriptionForm;
import sportEvents.service.OrganizerEventService;


import java.time.LocalDateTime;

@SpringBootApplication
public class SportEventsApplication extends SpringBootServletInitializer {

    @Autowired
    private UserService service;
    @Autowired
    EventsRepository eventsRepository;
    @Autowired
    OrganizerEventService organizerEventService;
    @Autowired
    PlayerSubscriptionService playerSubscriptionService;
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SportEventsApplication.class, args);
    }

    @Bean
    @Profile("dev")
    InitializingBean sendDatabase() {
        return () -> {
            // INITIALIZE

            final var user1 = new RegisterPlayerForm(
                    "Kryś123",
                    "123",
                    "krzys@wp.pl",
                    "Ul. Blazka",
                    "Gdynia",
                    "Polska",
                    "65-098",
                    "Krzysztof",
                    "Ptych",
                    "1984-03-01",
                    "",
                    "80",
                    "",
                    "123456789",
                    "500400300");

            final var user2 = new RegisterPlayerForm(
                    "julka456",
                    "julka456",
                    "julka_buziaczek@interia.pl",
                    "Ul. Markwarta",
                    "Toruń",
                    "Polska",
                    "34-876",
                    "Agnieczka",
                    "Shmidt",
                    "1990-12-16",
                    "Dzikie Julki",
                    "55",
                    "",
                    "908070",
                    "505789111");

            final var registeredUserId1 = service.registerPlayer(user1);
            final var registeredUserId2 = service.registerPlayer(user2);

            final var user3 = new RegisterOrganizerForm(
                    "kozak",
                    "rrx",
                    "knt.rrx_szef@gmail.com",
                    "Ogrodowa",
                    "Warszawa",
                    "Polska",
                    "99-876",
                    "knt.rrx_szef");

            final var registeredOrganizerId1 = service.registerOrganizer(user3);

            final var registeredEventId = organizerEventService.addEvent(new RegisterEventForm
                    (registeredOrganizerId1.getUserId(),
                            "Zawody pływackie 10.2021",
                            LocalDateTime.now().toString(),
                            "12", "15"));


            final var registeredSubscriptionId1 = playerSubscriptionService.addSubscription(
                    new RegisterSubscriptionForm(registeredUserId1.getUserId(),
                            true,
                            LocalDateTime.now(),
                            true,
                            registeredEventId.getEventId()));

            final var registeredSubscriptionId2 = playerSubscriptionService.addSubscription(
                    new RegisterSubscriptionForm(registeredUserId2.getUserId(),
                            true,
                            LocalDateTime.now(),
                            true,
                            registeredEventId.getEventId()));

        };
    }
}