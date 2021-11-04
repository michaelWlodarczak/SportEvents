package sportEvents.entity.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sportEvents.entity.Organizer;
import sportEvents.entity.Player;
import sportEvents.entity.User;
import sportEvents.service.dto.RegisterPlayerForm;

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
        assertEquals(List.of(user), result);
    }

    @Test
    public void shouldFindByEmail() {
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
        assertEquals(List.of(user), result);
    }

    @Test
    void shouldFindUserByEmail() {
        // given
        /* shouldSave() */
        // when
        User user = repository.findByUserEmail("krzys@wp.pl");
        // then
        assertNotNull(user);
    }

    @Test
    public void shouldGetUserById() {
        //given
        final var user1 = new Player(
                "walo15",
                "pasekNaDresie",
                "walo15@wp.pl",
                "Grochowska",
                "Olsztyn",
                "Polska",
                "11-345",
                "Waldemar",
                "Walasik",
                LocalDate.of(1989, 06, 25),
                "",
                65,
                "",
                "",
                "607892345");

        final var user2 = new Organizer(
                "Cjalis",
                "blok",
                "cjalis@onet.pl",
                "Piekna",
                "Piaseczno",
                "Polska",
                "09-130",
                "Cjalis");

        repository.saveAllAndFlush(List.of(user1, user2));
        //when & then
        if (user1 instanceof Player) {
            Player player = repository.getPlayerByUserId(user1.getUserId());
            assertTrue(user1.getUserId().equals(player.getUserId()));
        } else if (user2 instanceof Organizer) {
            Organizer organizer = repository.getOrganizerByUserId(user2.getUserId());
            assertTrue(user2.getUserId().equals(organizer.getUserId()));
        }

    }

    @Test
    void shouldGetUserById2() {
        // given
        /* shouldSave() */
        // when & then
        User user = repository.findByUserEmail("julka_buziaczek@interia.pl"); //player@player.com
        if (user instanceof Player) {
            Player player = repository.getPlayerByUserId(user.getUserId());
            assertTrue(user.getUserId().equals(player.getUserId()));
        } else if (user instanceof Organizer) {
            Organizer organizer = repository.getOrganizerByUserId(user.getUserId());
            assertTrue(user.getUserId().equals(organizer.getUserId()));
        }
    }

}