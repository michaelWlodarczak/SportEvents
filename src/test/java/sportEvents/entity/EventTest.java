package sportEvents.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sportEvents.service.dto.EventDetails;
import sportEvents.service.dto.EventView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    Event event = new Event();

    @BeforeEach
    public void setup(){
        Organizer mockOrganizer = Mockito.mock(Organizer.class);
        Mockito.when(mockOrganizer.getUserId()).thenReturn(UUID.randomUUID());
        Subscription mockSubscription = Mockito.mock(Subscription.class);
        List<Subscription> subscriptionList = new ArrayList<>();
        subscriptionList.add(mockSubscription);
        event.setOrganizer(mockOrganizer);
        event.setEventId(UUID.randomUUID());
        event.setEventTitle("EventTitle");
        event.setEventDate(LocalDateTime.now());
        event.setEventPlayerLimit(10);
        event.setEventFee(0.0d);
        event.setEventSubscriptions(subscriptionList);
    }

    @Test
    void toView(){
        //given setup()

        //when
        EventView eventView = event.toView();
        //then
        assertNotNull(eventView.getEventId());
        assertNotNull(eventView.getEventDate());
        assertEquals(eventView.getEventPlayerLimit(),10);
        assertEquals(eventView.getSubscriptionCount(),1);
    }

    @Test
    void toDetails(){
        //given setup()

        //when
        EventDetails eventDetails = event.viewDetail();
        //then
        assertNotNull(eventDetails.getEventId());
        assertNotNull(eventDetails.getEventDate());
        assertEquals(eventDetails.getEventPlayerLimit(),10);
        assertEquals(eventDetails.getPlayerSubscriptions().size(),1);
    }
}