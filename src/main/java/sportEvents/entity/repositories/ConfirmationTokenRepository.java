package sportEvents.entity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sportEvents.entity.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}

