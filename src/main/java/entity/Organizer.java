package entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("ORAGANIZER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Organizer extends User {

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "player_id")
    private List<Event> eventList;

    public Organizer(UUID userId, String login, String password, String email,
                     UserType userType, String streetWithNumber, String city,
                     String country, String zipCode, String name) {
        super(userId, login, password, email, userType, streetWithNumber, city, country, zipCode);
        this.name = name;
    }
}
