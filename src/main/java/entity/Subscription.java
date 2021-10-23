package entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@NoArgsConstructor(access = AccessLevel.PROTECTED) //  PRIVATE ?? for hibernate
@Getter
@EqualsAndHashCode
public class Subscription {

    @Id
    private UUID subscriptionId;
    private boolean subscriptionPaymentDone;
    private LocalDateTime dateOfPayment;
    private boolean subscriptionApproved;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    public Subscription(boolean subscriptionPaymentDone,
                        LocalDateTime dateOfPayment,
                        boolean subscriptionApproved,
                        Event event) {
        this.subscriptionId = UUID.randomUUID();
        this.subscriptionPaymentDone = subscriptionPaymentDone;
        this.dateOfPayment = dateOfPayment;
        this.subscriptionApproved = subscriptionApproved;
        this.event = event;
    }
}
