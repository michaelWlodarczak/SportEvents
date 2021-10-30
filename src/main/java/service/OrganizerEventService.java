package service;

import entity.Event;
import entity.Organizer;
import entity.enums.UserType;
import entity.repositories.EventsRepository;
import entity.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.dto.AddEventForm;
import service.dto.RegisterEventForm;
import service.dto.RegisteredEventId;
import service.dto.RemoveEventForm;
import service.exception.EventException;
import service.exception.SubscriptionException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@Transactional
@RequiredArgsConstructor
public class OrganizerEventService {
    @NonNull
    private UserRepository userRepository;
    @NonNull
    private EventsRepository eventsRepository;

    public RegisteredEventId addEvent(@NonNull RegisterEventForm form) {
        if (!(userRepository.getById(form.getUserId()).getUserType().equals(UserType.ORGANIZER))) {
            throw new SubscriptionException("The user is not an Organizer");
        }
        Organizer organizer = userRepository.getOrganizerByUserId(form.getUserId());
        Event event = new Event(
                form.getEventTitle(),
                LocalDateTime.parse(form.getEventDate()),
                Integer.valueOf(form.getEventPlayerLimit()),
                Double.valueOf(form.getEventFee()),
        organizer);
        organizer.addEvent(event);
        userRepository.save(organizer);
        return new RegisteredEventId(organizer.getUserId(), event.getEventId());
    }

    public UUID removeEvent(@NonNull RemoveEventForm form) {
        if(eventsRepository.findEventSubscriptions(eventsRepository.getById(form.getEventId())).size()>0){
            throw new EventException("There are subscriptions for this event. Cannot remove!");
        }
        Organizer organizer = userRepository.getOrganizerByUserId(form.getUserId());
        Event removedEvent = eventsRepository.getById(form.getEventId());
        UUID removedEventId = removedEvent.getEventId();
        organizer.removeEvent(removedEvent);
        userRepository.save(organizer);
        return removedEventId;
    }

    /* Prepare form for POST purposes (GET ID FORM URL PARAM) and POST IT
     * Added LocalDateTime auto generation if null
     *
     * */
    public RegisteredEventId addEventRest(@NonNull RegisterEventForm form, UUID userId){
        //Some validation rules
        //TODO Extend Date Validation Class
        String formDate = form.getEventDate();
        String formPlayerLimit = form.getEventPlayerLimit();
        String formEventFee = form.getEventFee(); //TODO - continue
    }
}
