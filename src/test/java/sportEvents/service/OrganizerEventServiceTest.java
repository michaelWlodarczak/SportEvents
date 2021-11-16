package sportEvents.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sportEvents.entity.*;
import sportEvents.entity.Organizer;
import sportEvents.entity.repositories.EventsRepository;
import sportEvents.entity.repositories.UserRepository;
import sportEvents.service.dto.RegisterEventForm;
import sportEvents.service.dto.RemoveEventForm;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class OrganizerEventServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private OrganizerEventService organizerEventService;

    @Test
    void shouldAddEventToOrganizer() {
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
        final var event = new RegisterEventForm(
                organizer.getUserId(),
                "TitleEvent",
                LocalDateTime.now().toString(),
                "10",
                "0"
        );
        final var addedEvent = organizerEventService.addEvent(event);

        assertNotNull(addedEvent);
        assertEquals(organizer.getOrganizerEvents().size(),1);
        assertEquals(organizer.getOrganizerEvents().get(0).getEventId(),addedEvent.getEventId());
    }
    @Test
    void shouldRemoveEventFromOrganizer() {
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
        final var event = new RegisterEventForm(
                organizer.getUserId(),
                "TitleEvent",
                LocalDateTime.now().toString(),
                "10",
                "0"
        );

        final var addedEvent = organizerEventService.addEvent(event);
        final var removeEventForm = new RemoveEventForm(
                organizer.getUserId(),addedEvent.getEventId()
                );

        assertNotNull(addedEvent);
        assertEquals(organizer.getOrganizerEvents().size(),1);
        assertEquals(organizer.getOrganizerEvents().get(0).getEventId(),addedEvent.getEventId());
       organizerEventService.removeEvent(removeEventForm);
        assertEquals(organizer.getOrganizerEvents().size(),0);
    }
}