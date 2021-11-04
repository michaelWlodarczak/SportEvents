package sportEvents.service;

import sportEvents.entity.Event;
import sportEvents.entity.Organizer;
import sportEvents.entity.enums.UserType;
import sportEvents.entity.repositories.EventsRepository;
import sportEvents.entity.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sportEvents.service.dto.*;
import sportEvents.service.exception.EventException;
import sportEvents.service.exception.SubscriptionException;

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

    public RegisteredEventId addEvent(@NonNull RegisterEventForm form){
        if (!(userRepository.getUserType(form.getUserId()).equals(UserType.ORGANIZER))) {
            throw new SubscriptionException("Given user is not a Organizer");
        }
        Organizer organizer = userRepository.getOrganizerByUserId(form.getUserId());
        Event event = new Event(
                form.getEventTitle(),
                LocalDateTime.parse(form.getEventDate()),
                Integer.valueOf(form.getEventPlayerLimit()),
                Double.parseDouble(form.getEventFee()),
                organizer);
        organizer.addEvent(event);
        userRepository.save(organizer);
        return new RegisteredEventId(organizer.getUserId(),event.getEventId());
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
        String formEventFee = form.getEventFee();
        if(form.getEventDate().equals("")){
            throw new EventException("Event must have Title!");
        }
        if (eventsRepository.findByEventTitle(form.getEventTitle()).size()>0){
            throw new EventException("Event with this title already exists");
        }
        if (formDate.equals("")){
            formDate = LocalDateTime.now().toString();
        }
        if(Integer.parseInt(formPlayerLimit)>0){
            formPlayerLimit = String.valueOf(0);
        }
        if (Double.parseDouble(formEventFee)>0){
            formEventFee = String.valueOf(0.0d);
        }
        RegisterEventForm userAddedForm = new RegisterEventForm(
                userId,
                form.getEventTitle(),
                formDate,
                formPlayerLimit,
                formEventFee);
        return addEvent(userAddedForm);
    }

    public DeletedEventId removeEventRest(@NonNull RemoveEventForm form,UUID userId){
        RemoveEventForm subForm = new RemoveEventForm(
                userId,form.getEventId()
        );
        UUID removedEvent = removeEvent(subForm);
        if (removedEvent == null){
            throw new EventException("Event removing problems!");
        }
        return new DeletedEventId(userId,removedEvent);
    }
}
