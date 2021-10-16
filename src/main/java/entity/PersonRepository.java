package entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<User, UUID> {
    List<User> findByLastName (String lastName);
    List<User> fingByEmailAddress(String emailAddress);

    List<Player> findByTeam(String teamName);
    List<Organizer> findByName(String organizatorName);
}
