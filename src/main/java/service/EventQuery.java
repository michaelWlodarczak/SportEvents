package service;

import entity.Event;
import entity.repositories.EventsRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventQuery {

    @NonNull
    private final EventsRepository eventsRepository;

//    public List<CustomerView> listCustomers() {
//        return repository.findAll().stream()
//                .map(customer -> new CustomerView(customer.getId(),
//                        customer.getName(),
//                        customer.getEmail(),
//                        customer.getCustomerType()))
//                .collect(Collectors.toList()); //Collector mozna zaimportowac statycznie
//
//    }

    //TODO
//    public List<EventView> listEvent(){
//
//    }

//    public CustomerDetails getById(UUID customerId) {
//        return repository.findById(customerId)
//                .orElseThrow(() -> new CustomerNotExistsException("customer not found: " + customerId))
//                .mapToDetails();
//    }

    //TODO
//    public EventDetails getEventDetails(){
//
//    }

}
