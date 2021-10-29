package service;

import entity.Event;
import entity.repositories.EventsRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import service.dto.EventDetails;
import service.dto.EventView;

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
