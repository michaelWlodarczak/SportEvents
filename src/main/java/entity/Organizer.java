package entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
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


    public Organizer(String login,
                     String password,
                     String email,
                     String streetWithNumber,
                     String city,
                     String country,
                     String zipCode,
                     String name,
                     List<Event> eventList) {
        super(login, password, email, streetWithNumber, city, country, zipCode);
        this.name = name;
        this.eventList = eventList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organizer)) return false;
        Organizer organizer = (Organizer) o;
        return Objects.equals(getName(),
                organizer.getName()) && Objects.equals(getEventList(), organizer.getEventList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEventList());
    }
}
