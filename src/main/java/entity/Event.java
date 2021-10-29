package entity;

import lombok.*;
import service.exception.SubscriptionException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Event {

    @Id
    private UUID eventId;
    private String eventTitle;
    private LocalDateTime eventDate;
    private int eventPlayerLimit;
    private double eventFee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="event",orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Subscription> eventSubscriptions;

    @OneToMany
    @JoinColumn(name = "events_id",nullable = false)
    private Organizer organizer;

    public Event(@NonNull String eventTitle,
                 @NonNull LocalDateTime eventDate,
                 @NonNull Integer eventPlayerLimit,
                 @NonNull double eventFee) {
        this.eventId = UUID.randomUUID();
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventPlayerLimit = eventPlayerLimit;
        this.eventFee = eventFee;
    }

    public void removeSubscription(Subscription subscription){
        if(subscription != null) {
            if( eventSubscriptions.contains(subscription)){
                eventSubscriptions.remove(subscription);
            } else {
                throw new SubscriptionException("Subscription for this event not exist for this Player");
            }
        }
    }



//    public EventView toView(){
//        return new EventView(eventId,
//                eventTitle,
//                eventDate,
//                eventPlayerLimit,
//                eventFee,
//                eventSubscriptions.size());
//    }

    //TODO
//    public EventDetails viewDetail(){
//        return new EventDetails(getEventId(),
//                getEventTitle(),
//                getEventDate(),
//                getEventPlayerLimit(),
//                getEventFee(),
//                getEventSubscriptions().stream().map(Subscription::toEventView).collect(Collectors.toList()));
//
//    }
}
