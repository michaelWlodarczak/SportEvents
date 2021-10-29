package service;

import entity.Event;
import entity.Organizer;
import entity.enums.UserType;
import entity.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.dto.AddEventForm;
import service.dto.RegisteredEventId;
import service.dto.RemoveEventForm;
import service.exception.SubscriptionException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Service
@Transactional
@RequiredArgsConstructor
public class OrganizerEventService {
    @NonNull
    private final UserRepository userRepository;

    public RegisteredEventId addEvent(@NonNull AddEventForm form) {
        if (userRepository.getById(form.getUserId()) == null) {
            throw new SubscriptionException("");
        }
        if (!(userRepository.getById(form.getUserId()).getUserType().equals(UserType.ORGANIZER))) {
            throw new SubscriptionException("The user is not an Organizer");
        }
        Organizer organizer = userRepository.getOrganizerByUserId(form.getUserId());
        Event event = new Event(
                form.getEventTitle(),
                LocalDateTime.now(),
                form.getEventPlayerLimit(),
                form.getEventFee());
        organizer.addEvent(event);
        userRepository.save(organizer);
        return new RegisteredEventId(organizer.getUserId(), event.getEventId());
    }

    public void removeEvent(@NonNull RemoveEventForm form) {
        Organizer organizer = userRepository.getOrganizerByUserId(form.getUserId());
        organizer.removeEvent(form.getEvent());
        userRepository.save(organizer);
    }

}
