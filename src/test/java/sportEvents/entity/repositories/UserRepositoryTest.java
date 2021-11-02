package sportEvents.entity.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sportEvents.entity.Organizer;
import sportEvents.entity.Player;
import sportEvents.entity.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")  //TODO sprawdzic adnotacje
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    public void shouldSave() {
        //given
        final var user1 = new Player(
                "cieślak123",
                "golf",
                "cieslak123@wp.pl",
                "Wiatraczna",
                "Warszawa",
                "Polska",
                "12-345",
                "Pawel",
                "Ciesielski",
                LocalDate.of(1990, 04, 12),
                "",
                99,
                "",
                "987123",
                "506445398");

        final var user2 = new Organizer(
                "Cjalis",
                "blok",
                "cjalis@onet.pl",
                "Piekna",
                "Piaseczno",
                "Polska",
                "09-130",
                "Cjalis");

        //when
        repository.saveAllAndFlush(List.of(user1, user2));

        //then
        assertEquals(2, repository.count());
        assertEquals(repository.getById(user1.getUserId()), user1);
    }

    @Test
    public void shouldFindByName() {
        //given
        final var user = new Organizer(
                "Cjalis",
                "blok",
                "cjalis@onet.pl",
                "Piekna",
                "Piaseczno",
                "Polska",
                "09-130",
                "Cjalis");
        repository.saveAllAndFlush(List.of(user));
        //when
        final var result = repository.findByOrganizerName("Cjalis");
        //then
        assertEquals(List.of(user),result);
    }

    @Test
    public void shouldFindByEmail(){
        //given
        final var user = new Organizer(
                "Cjalis",
                "blok",
                "cjalis@onet.pl",
                "Piekna",
                "Piaseczno",
                "Polska",
                "09-130",
                "Cjalis");
        repository.saveAllAndFlush(List.of(user));
        //when
        final var result = repository.findByUserEmail("cjalis@onet.pl");
        //then
        assertEquals(List.of(user),result);
    }

    @Test
    void shouldFindUserByEmail() {
        // given
        /* shouldSave() */
        // when
        User user = repository.findByUserEmail("m@wp.pl");
        // then
        assertNotNull(user);
    }




}