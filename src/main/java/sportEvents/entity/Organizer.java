package sportEvents.entity;

import lombok.*;
import sportEvents.service.dto.OrganizerDetails;
import sportEvents.service.dto.OrganizerView;
import sportEvents.service.dto.RegisterOrganizerForm;
import sportEvents.service.exception.EventException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("ORGANIZER")
@Getter
@Setter()
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Organizer extends User {
    private String organizerName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizer", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Event> organizerEvents;

    public Organizer(String userPassword,
                     String userLogin,
                     String userEmail,
                     String userCity,
                     String userStreet,
                     String userCountry,
                     String userZipCode,
                     @NonNull String organizerName) {
        super(userPassword, userLogin, userEmail, userCity, userStreet, userCountry, userZipCode);
        this.organizerName = organizerName;
        this.organizerEvents = new ArrayList<>();
    }

    public static Organizer createWith(RegisterOrganizerForm form) {
        return new Organizer(form.getUserPassword(),
                form.getUserLogin(),
                form.getUserEmail(),
                form.getUserCity(),
                form.getUserStreet(),
                form.getUserCountry(),
                form.getUserZipCode(),
                form.getOrganizerName());
    }

    public static Organizer updateOrganizer(RegisterOrganizerForm form, Organizer organizer) {
        organizer.setUserPassword(form.getUserPassword());
        organizer.setUserLogin(form.getUserLogin());
        //organizer.setUserEmail(form.getUserEmail()),
        organizer.setUserCity(form.getUserCity());
        organizer.setUserStreet(form.getUserStreet());
        organizer.setUserCountry(form.getUserCountry());
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
                throw new EventException("Event for this organizer not exist");
            }
        }
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

    public OrganizerView toOrganizerView() {
        return new OrganizerView(getUserId(),
                getName(),
                getUserEmail(),
                getUserType(),
                getOrganizerEvents().size(),
                isUserActive());
    }

    public OrganizerDetails viewDetail() {
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
