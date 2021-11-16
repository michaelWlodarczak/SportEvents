package sportEvents;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import sportEvents.entity.User;
import sportEvents.entity.repositories.EventsRepository;
import sportEvents.entity.repositories.UserRepository;
import sportEvents.service.OrganizerEventService;
import sportEvents.service.PlayerSubscriptionService;
import sportEvents.service.UserMaintenanceService;
import sportEvents.service.UserService;
import sportEvents.service.dto.RegisterEventForm;
import sportEvents.service.dto.RegisterOrganizerForm;
import sportEvents.service.dto.RegisterPlayerForm;
import sportEvents.service.dto.RegisterSubscriptionForm;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class SportEventsApplication extends SpringBootServletInitializer {

    @Autowired
    private UserService service;
    @Autowired
    private UserMaintenanceService userMaintenanceService;
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
        InitializingBean adminData() {
            return () -> {
                final var admin = new RegisterOrganizerForm("admin",
                        "admin",
                        "admin@admin.com",
                        "",
                        "",
                        "",
                        "",
                        "SportEvent"
                );
                final var registeredOrganizerId = service.registerOrganizer(admin);
                userMaintenanceService.activateUser(registeredOrganizerId.getUserId());
                service.updateUserRoles(registeredOrganizerId.getUserId(),"ROLE_ADMIN");

            };
        }

        @Bean
        @Profile("dev")
        InitializingBean sampleData() {
            return () -> {
                // INITIALIZE

                final var user1 = new RegisterPlayerForm("12345",
                        "ptych",
                        "ptych@wp.pl",
                        "Gdynia",
                        "Swietojanska",
                        "Poland",
                        "97345",
                        "Krzysztof",
                        "Ptych",
                        "1991-10-14",
                        "",
                        "80",
                        "",
                        "",
                        "500600700");
                final var registeredUserId = service.registerPlayer(user1);

                final var user2 = new RegisterOrganizerForm("98765",
                        "OSiR",
                        "osir@interia.com",
                        "Bydgoszcz",
                        "Torunska",
                        "Poland",
                        "65398",
                        "OSiR"
                );

                final var registeredOrganizerId = service.registerOrganizer(user2);
                final var registeredEventId = organizerEventService.addEvent(new RegisterEventForm(
                        registeredOrganizerId.getUserId(),
                        "Zawody pływackie",
                        LocalDateTime.now().toString(),
                        "14",
                        "10"
                ));
                final var registeredSubscriptionId = playerSubscriptionService.addSubscription(
                        new RegisterSubscriptionForm(
                                registeredUserId.getUserId(),
                                true,
                                LocalDateTime.now().toString(),
                                true,
                               registeredEventId.getEventId()
                        ));
                User user = userRepository.getOrganizerByUserId(registeredOrganizerId.getUserId());
                user.setUserRoles(Arrays.asList("ROLE_ADMIN","ROLE_USER"));
                userRepository.save(user);
              /*playerSubscriptionService.removeSubscription(new RemoveSubscriptionForm(
                      registeredUserId.getUserId(),
                      eventsRepository.getById(registeredEventId.getEventId())
                ));*/
            };
        }
}
