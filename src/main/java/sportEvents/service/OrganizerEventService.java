package sportEvents.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sportEvents.entity.*;
import sportEvents.entity.enums.UserType;
import sportEvents.entity.repositories.EventsRepository;
import sportEvents.entity.repositories.UserRepository;
import sportEvents.service.dto.*;
import sportEvents.service.exception.EventException;
import sportEvents.service.exception.SubscriptionException;
import sportEvents.entity.Event;
import sportEvents.entity.Organizer;
import sportEvents.service.dto.DeletedEventId;
import sportEvents.service.dto.RegisterEventForm;
import sportEvents.service.dto.RegisteredEventId;
import sportEvents.service.dto.RemoveEventForm;

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
    public UUID removeEvent(@NonNull RemoveEventForm form){
        if (eventsRepository.findEventSubscriptions(eventsRepository.getById(form.getEventId())).size() > 0){
            throw new EventException("There are subscriptions for this event ! COULD NOT REMOVE !");
        } //TODO FIND ACTIVE AND DEL ALL OTHERS BEFORE
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

        if (form.getEventTitle().equals("")) {
            throw new EventException("Event must have a TITLE!");
        }
        if (eventsRepository.findByEventTitle(form.getEventTitle()).size() > 0) {
            throw new EventException("Event with given TITLE exists !");
        }

        if (formDate.equals("") ) {
            formDate = LocalDateTime.now().toString();
        }
        if (Integer.parseInt(formPlayerLimit) > 0){
            formPlayerLimit = String.valueOf(0);
        }
        if (Double.parseDouble(formEventFee) > 0){
            formEventFee = String.valueOf(0.0d);
        }
        //
        RegisterEventForm userAddedForm = new RegisterEventForm(
                userId,
                form.getEventTitle(),
                formDate,
                formPlayerLimit,
                formEventFee
        );
        return addEvent(userAddedForm);
    }
    public DeletedEventId removeEventRest(@NonNull RemoveEventForm form, UUID userId){
        RemoveEventForm subform = new RemoveEventForm(
                userId,
                form.getEventId()
        );
        UUID removedEvent = removeEvent(subform);
        if (removedEvent == null){
            throw new SubscriptionException("Event removing problem !");
        }
        return new DeletedEventId(userId,removedEvent);
    }
    //TODO write UPDATEEVENT - after update send email / notification for subscribers
}
