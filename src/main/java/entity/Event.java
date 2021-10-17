package entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String eventName;
    private LocalDateTime eventDate;
    private int playerLimit;
    private double eventFee;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private List<Subscription> eventSubscriptions;

    public Event(String eventName,
                 LocalDateTime eventDate,
                 int playerLimit,
                 double eventFee,
                 List<Subscription> eventSubscriptions) {
        this.eventId = UUID.randomUUID();
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.playerLimit = playerLimit;
        this.eventFee = eventFee;
        this.eventSubscriptions = new ArrayList<>();
    }
}
