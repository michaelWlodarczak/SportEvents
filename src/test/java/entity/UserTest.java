package entity;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void shouldCreateUser(){
        //given
        User user = new User(
                "osprey",
                "123",
                "osprey.drboats@gmail.com",
                "Swietojanska",
                "Gdynia",
                "Poland",
                "88-0987")
        {
            @Override
            public String getName() {
                return null;
            }
        };

        assertNotNull(user);
        assertTrue(user.getUserId() != null);
    }

}
