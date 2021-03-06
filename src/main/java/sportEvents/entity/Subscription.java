package sportEvents.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import sportEvents.service.dto.SubscriptionEventView;
import sportEvents.service.dto.SubscriptionView;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "subscriptions")
@NoArgsConstructor(access = AccessLevel.PRIVATE) // for hibernate
@Getter
@Setter
@EqualsAndHashCode

public class Subscription {
    @Id
    @Type(type="uuid-char")
    private UUID subscriptionId;
    private boolean subscriptionPaymentDone;
    private LocalDateTime subscriptionDate;
    private boolean subscriptionApproved;
    @ManyToOne()
    @JoinColumn(name = "player_id",nullable=false)
    Player player;
    @ManyToOne()
    @JoinColumn(name = "events_id",nullable=false)
    private Event event;

    public Subscription(Boolean subscriptionPaymentDone,
                        LocalDateTime subscriptionDate,
                        Boolean subscriptionApproved,
                        Event event, Player player) {
        this.subscriptionId =  UUID.randomUUID();
        this.subscriptionPaymentDone = subscriptionPaymentDone;
        this.subscriptionDate = subscriptionDate;
        this.subscriptionApproved = subscriptionApproved;
        this.event = event;
        this.player = player;
    }

    public SubscriptionView toView(){
        return new SubscriptionView(
                subscriptionId.toString(),
                Boolean.toString(subscriptionPaymentDone),
                subscriptionDate.toString(),
                Boolean.toString(subscriptionApproved),
                event.getEventTitle(),
                event.getEventDate().toString(),
                event.getEventId().toString());
    }
    public SubscriptionEventView toEventView(){
        return new SubscriptionEventView( subscriptionId.toString(),
                Boolean.toString(subscriptionPaymentDone),
                subscriptionDate.toString(),
                Boolean.toString(subscriptionApproved),
                event.getEventTitle(),
                event.getEventDate().toString(),
                getPlayer().getUserId().toString(),
                getPlayer().getUserEmail());
    }
}
