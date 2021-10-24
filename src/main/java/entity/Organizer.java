package entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import service.dto.RegisterOrganizerForm;
import service.exception.EventException;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("ORAGANIZER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Organizer extends User {

    private String organizerName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "organizer_id")
    private List<Event> organizerEvents;

    public Organizer(String userLogin,
                     String userPassword,
                     String userEmail,
                     String userStreet,
                     String userCity,
                     String userCountry,
                     String userZipCode,
                     @NonNull String organizerName) {
        super(userLogin, userPassword, userEmail, userStreet, userCity, userCountry, userZipCode);
        this.organizerName = organizerName;
        this.organizerEvents = new ArrayList<>();
    }

    @Override
    public String getName() {
        return organizerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Organizer organizer = (Organizer) o;
        return organizerName.equals(organizer.organizerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), organizerName);
    }

    public static Organizer createWith(RegisterOrganizerForm form) {
        return new Organizer(form.getUserLogin(),
                form.getUserPassword(),
                form.getUserStreet(),
                form.getUserCity(),
                form.getUserCountry(),
                form.getUserZipCode(),
                form.getOrganizerName());
    }

    public void addEvent(Event event) {
        if (event != null) {
            if (!organizerEvents.contains(event)){
                organizerEvents.add(event);
            }else {
                throw new EventException("Event already exist for this Organizer");
            }
        }
    }
    public void removeEvent(Event event){
        if (event != null){
            if (organizerEvents.contains(event)){
                organizerEvents.remove(event);
            }else {
                throw new EventException("Event do not exist for this Organizer");
            }
        }
    }

}
