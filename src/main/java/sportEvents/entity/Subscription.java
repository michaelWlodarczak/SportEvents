package sportEvents.entity;

import lombok.*;
import sportEvents.service.dto.SubscriptionEventView;
import sportEvents.service.dto.SubscriptionView;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@NoArgsConstructor(access = AccessLevel.PROTECTED) //  PRIVATE ?? for hibernate
@Getter
@Setter
@EqualsAndHashCode
public class Subscription {

    @Id
    private UUID subscriptionId;
    private boolean subscriptionPaymentDone;
    private LocalDateTime dateOfPayment;
    private boolean subscriptionApproved;
    @ManyToOne()
    @JoinColumn(name = "player_id",nullable = false)
    Player player;
    @ManyToOne()
    @JoinColumn(name = "events_id",nullable = false)
    private Event event;

    public Subscription(Boolean subscriptionPaymentDone,
                        LocalDateTime dateOfPayment,
                        Boolean subscriptionApproved,
                        Event event, Player player) {
        this.subscriptionId = UUID.randomUUID();
        this.subscriptionPaymentDone = subscriptionPaymentDone;
        this.dateOfPayment = dateOfPayment;
        this.subscriptionApproved = subscriptionApproved;
        this.event = event;
        this.player = player;
    }

    public SubscriptionView toView() {
        return new SubscriptionView (subscriptionId.toString(),
                Boolean.toString(subscriptionPaymentDone),
                dateOfPayment.toString(),
                Boolean.toString(subscriptionApproved),
                event.getEventTitle(),
                event.getEventDate().toString(),
                event.getEventId().toString());
    }


    public SubscriptionEventView toEventView(){
        return new SubscriptionEventView(subscriptionId.toString(),
                Boolean.toString(subscriptionPaymentDone),
                dateOfPayment.toString(),
                Boolean.toString(subscriptionApproved),
                event.getEventTitle(),
                event.getEventDate().toString(),
                getPlayer().getUserId().toString(),
                getPlayer().getUserEmail());
    }
}
