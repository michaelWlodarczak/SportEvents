
import entity.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import service.OrganizerEventService;
import service.PlayerSubscriptionService;
import service.UserService;

import javax.transaction.Transactional;

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

        public static void main(String[] args) {
            SpringApplication.run(SportEventsApplication.class, args);
        }


}
