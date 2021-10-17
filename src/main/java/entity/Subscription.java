package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // for hibernate
@Getter
@EqualsAndHashCode
public class Subscription {

    @Id
    private UUID subscriptionId;
    private boolean subscriptionPayment;
    private LocalDateTime dateOfPayment;
    private boolean approved;

    public Subscription(UUID subscriptionId, boolean subscriptionPayment,
                        LocalDateTime dateOfPayment, boolean approved) {
        this.subscriptionId = subscriptionId;
        this.subscriptionPayment = subscriptionPayment;
        this.dateOfPayment = dateOfPayment;
        this.approved = approved;
    }

}
