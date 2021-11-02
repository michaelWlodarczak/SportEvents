package sportEvents.service;

import sportEvents.entity.Event;
import sportEvents.entity.repositories.EventsRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sportEvents.service.dto.EventDetails;
import sportEvents.service.dto.EventView;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventQuery {

    @NonNull
    private final EventsRepository eventsRepository;

    public List<EventView> eventList(){
        List<EventView> collect = eventsRepository.findAll().stream()
                .map(Event::toView)
                .collect(Collectors.toList());
        return collect;
    }
    public EventDetails getEventDetails(UUID eventId){
        return eventsRepository.getById(eventId).viewDetail();
    }
}
