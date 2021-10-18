package entity.repositories;

import entity.Organizer;
import entity.Player;
import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {


    List<User> findByLastName (String lastName);
    List<User> fingByEmailAddress(String emailAddress);

    List<Player> findByTeam(String teamName);
    List<Organizer> findByName(String organizatorName);

    //Create Player / Organizer
    //Delete Player / Organizer
    //SelectByEmail
    //SelectByUUID
    //SelectByLogin
    //SelectByTeam
    //SelectByName
    //SelectByType


}
