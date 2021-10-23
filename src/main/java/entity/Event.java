package entity;

import lombok.*;

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

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "event_id")
//    private List<Subscription> eventSubscriptions;

    public Event(@NonNull String eventTitle,
                 @NonNull LocalDateTime eventDate,
                 @NonNull int eventPlayerLimit,
                 @NonNull double eventFee) {
        this.eventId = UUID.randomUUID();
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventPlayerLimit = eventPlayerLimit;
        this.eventFee = eventFee;

    }
}
