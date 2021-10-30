package entity;

import lombok.*;
import service.dto.OrganizerDetails;
import service.dto.OrganizerView;
import service.dto.RegisterOrganizerForm;
import service.exception.EventException;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("ORAGANIZER")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Organizer extends User {

    private String organizerName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizer", orphanRemoval = true, fetch = FetchType.EAGER)
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
                form.getUserEmail(),
                form.getUserStreet(),
                form.getUserCity(),
                form.getUserCountry(),
                form.getUserZipCode(),
                form.getOrganizerName());
    }

    public static Organizer updateOrganizer(RegisterOrganizerForm form, Organizer organizer) {
        organizer.setUserLogin(form.getUserLogin());
        organizer.setUserPassword(form.getUserPassword());
        //email?
        organizer.setUserStreet(form.getUserStreet());
        organizer.setUserCity(form.getUserCity());
        organizer.setUserCity(form.getUserCity());
        organizer.setUserZipCode(form.getUserZipCode());
        organizer.setOrganizerName(form.getOrganizerName());
        return organizer;
    }

    public void addEvent(Event event) {
        if (event != null) {
            if (!organizerEvents.contains(event)) {
                organizerEvents.add(event);
            } else {
                throw new EventException("Event already exist for this Organizer");
            }
        }
    }

    public void removeEvent(Event event) {
        if (event != null) {
            if (organizerEvents.contains(event)) {
                organizerEvents.remove(event);
            } else {
                throw new EventException("Event do not exist for this Organizer");
            }
        }
    }

    public OrganizerView toOrganizerView(){
        return new OrganizerView(getUserId(),
                getName(),
                getUserEmail(),
                getUserType(),
                getOrganizerEvents().size(),
                isUserActive());
    }

    public OrganizerDetails viewDetail(){
        return new OrganizerDetails(getUserId(),
                getOrganizerName(),
                getUserEmail(),
                getUserType(),
                getUserCity(),
                getUserStreet(),
                getUserCountry(),
                getUserZipCode(),
                getOrganizerEvents().stream().map(Event::toView).collect(Collectors.toList())
        );
    }

}
