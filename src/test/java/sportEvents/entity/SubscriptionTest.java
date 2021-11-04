package sportEvents.entity;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import sportEvents.service.dto.SubscriptionView;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SubscriptionTest {

    @Mock
    Player playerMock;
    @Mock
    Event eventMock;
    @Mock
    Subscription subscriptionMock;

    @Test
    public void shouldCreateSubscription(){
        //given
        Subscription subscription = new Subscription(true,
                LocalDateTime.now(),
                true,
                eventMock,
                playerMock);
        //when & then
        assertNotNull(subscription);
        assertNotNull(subscription.getSubscriptionId());
        assertEquals(subscription.getEvent(),eventMock);
        assertEquals(subscription.getPlayer(),playerMock);
    }

    @Test
    public void shouldCreateView(){
        //given
        String testTitle = "TITLE";
        //when
        Event eventMock = mock(Event.class);
        when(eventMock.getEventTitle()).thenReturn(testTitle);
        when(eventMock.getEventDate()).thenReturn(LocalDateTime.now());
        when(eventMock.getEventId()).thenReturn(UUID.randomUUID());
        Subscription subscription = new Subscription(true,
                LocalDateTime.now(),
                true,
                eventMock,
                playerMock);
        SubscriptionView subscriptionView = subscription.toView();
        //then
        assertEquals(subscriptionView.getEventTitle(),testTitle);
    }
}