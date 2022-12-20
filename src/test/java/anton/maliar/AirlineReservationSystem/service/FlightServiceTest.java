package anton.maliar.AirlineReservationSystem.service;

import anton.maliar.AirlineReservationSystem.repository.FlightRepository;
import anton.maliar.AirlineReservationSystem.repository.model.Flight;
import anton.maliar.AirlineReservationSystem.repository.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceTest {
    private FlightService flightService;
    private FlightRepository flightRepository;
    private HttpServletRequest request;

    @BeforeEach
    public void init(){
        flightRepository = mock(FlightRepository.class);
        flightService = new FlightService(flightRepository);
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void getFreeSeatsWithoutReserveSeats(){
        Flight flight = mock(Flight.class);
        when(flight.getNumberSeats()).thenReturn(5);
        when(flight.getReserveSeats()).thenReturn(List.of());

        List<Integer> testList = flightService.getFreeSeats(flight);
        List<Integer> expectedList = List.of(1,2,3,4,5);

        assertArrayEquals(expectedList.toArray(), testList.toArray());
    }
    @Test
    public void getFreeSeatsWitReserveSeats(){
        Flight flight = mock(Flight.class);
        Ticket ticket1 = mock(Ticket.class);
        Ticket ticket2 = mock(Ticket.class);
        Ticket ticket3 = mock(Ticket.class);

        when(flight.getNumberSeats()).thenReturn(6);
        when(flight.getReserveSeats()).thenReturn(List.of(ticket1, ticket2, ticket3));
        when(ticket1.getPlace()).thenReturn(1);
        when(ticket2.getPlace()).thenReturn(3);
        when(ticket3.getPlace()).thenReturn(5);

        List<Integer> testList = flightService.getFreeSeats(flight);
        List<Integer> expectedList = List.of(2,4,6);

        assertArrayEquals(expectedList.toArray(), testList.toArray());
    }

}

































