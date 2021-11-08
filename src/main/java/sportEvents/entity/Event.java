package sportEvents.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;
import sportEvents.service.dto.EventDetails;
import sportEvents.service.dto.EventView;
import sportEvents.service.exception.SubscriptionException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "events")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode
public class Event {

    @Id
    @Type(type="uuid-char")
    private UUID eventId;
    private String eventTitle;
    private LocalDateTime eventDate;
    private int eventPlayerLimit;
    private double eventFee;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Subscription> eventSubscriptions;
    @ManyToOne()
    @JoinColumn(name = "events_id", nullable = false)
    private Organizer organizer;

    public Event(@NotNull String eventTitle,
                 @NotNull LocalDateTime eventDate,
                 @NotNull Integer eventPlayerLimit,
                 @NotNull double eventFee,
                 @NotNull Organizer organizer) {
        this.eventId = UUID.randomUUID();
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventPlayerLimit = eventPlayerLimit;
        this.eventFee = eventFee;
        this.organizer = organizer;
        this.eventSubscriptions = new ArrayList<>();
    }

    public void addSubscription(Subscription subscription) {
        if (subscription != null) {
            if (!eventSubscriptions.contains(subscription)) {
                eventSubscriptions.add(subscription);
            } else {
                throw new SubscriptionException("Subscription for this event is on the list of this Player");
            }
        }
    }

    public void removeSubscription(Subscription subscription) {
        if (subscription != null) {
            if (eventSubscriptions.contains(subscription)) {
                eventSubscriptions.remove(subscription);
            } else {
                throw new SubscriptionException("Subscription for this event not exist for this Player");
            }
        }
    }

    public EventView toView() {
        return new EventView(eventId,
                eventTitle,
                eventDate,
                eventPlayerLimit,
                eventFee,
                eventSubscriptions.size());
    }

    public EventDetails viewDetail() {
        return new EventDetails(getEventId(),
                getOrganizer().getUserId(),
                getEventTitle(),
                getEventDate(),
                getEventPlayerLimit(),
                getEventFee(),
                getEventSubscriptions().stream().map(Subscription::toEventView).collect(Collectors.toList()));
    }
}
