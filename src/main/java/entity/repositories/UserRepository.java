package entity.repositories;

import entity.*;
import entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUserEmail(String userEmail);
    Player getPlayerByUserId(UUID userId);
    Organizer getOrganizerByUserId(UUID userId);
    List<User> findByUserActive(boolean active);
    List<User> findByUserLogin(String userLogin);
    List<Player> findByPlayerTeamName(String playerTeamName);
    List<Organizer> findByOrganizerName(String organizerName);
    @Query("select u.userType from User u where u.userId = upper(?1)")
    UserType getUserType(UUID userId);
    @Query("from User u where u.userType = entity.enums.UserType.PLAYER") //TODO potwierdzic sciezke
    List<Player> getAllPlayers ();
    @Query("from User u where u.userType = entity.enums.UserType.ORGANIZER")
    List<Organizer> getAllOrganizers ();
    Player findPlayerByUserId(UUID userId);
    @Query("select (count(u) > 0) from User u where upper(u.userEmail) = upper(?1)")
    boolean emailExists(String email);
    @Query("select p.playerSubscriptions from Player p where p.userEmail = ?1")
    List<Subscription> findSubscriptionsByUserEmail (String userEmail); //findSubscriptionsForUserEmail
}
