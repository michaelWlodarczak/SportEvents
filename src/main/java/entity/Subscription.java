package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
@Entity
@Table(name = "subscriptions")
@NoArgsConstructor(access = AccessLevel.PRIVATE) // for hibernate
@Getter
@EqualsAndHashCode
public class Subscription {
    @Id
    private UUID subscriptionId;
    private User subscriptionUser;
    private Boolean subscriptionPayment;

    public Subscription(@NonNull UUID subscriptionId,
                        @NonNull User subscriptionUser,
                        @NonNull Boolean subscriptionPayment) {
        this.subscriptionId = UUID.randomUUID();
        this.subscriptionUser = subscriptionUser;
        this.subscriptionPayment = subscriptionPayment;
    }
}
