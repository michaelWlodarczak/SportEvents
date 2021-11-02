package sportEvents.entity.repositories;

import sportEvents.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    Subscription findByEvent_EventIdAndPlayer_UserId(UUID event_id, UUID user_id);
    Subscription findFirstByEvent_EventIdAndPlayer_UserId(UUID event_id, UUID user_id);

}
