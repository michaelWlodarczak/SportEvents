package entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventsRepository extends JpaRepository <Event, UUID> {

    List<Event> findByEventName (String eventName);
    List<Event> findByOrganizator (Organizer organizer);
    List<Event> findByEventDate (LocalDateTime eventDate);
}
