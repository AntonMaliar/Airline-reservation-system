package anton.maliar.AirlineReservationSystem.repository;

import anton.maliar.AirlineReservationSystem.repository.model.Flight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FlightRepositoryTest {
    private FlightRepository flightRepository;
    private List<Flight> flights;

    @Autowired
    public FlightRepositoryTest(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }
    @BeforeEach
    public void init(){
        flights = new ArrayList<>();
        flights.add(new Flight(
                "Kiev",
                "Warsaw",
                "AN-123",
                100,
                LocalDate.of(2022,1,1),
                LocalTime.of(12,0)));
        flights.add(new Flight(
                "Kiev",
                "Warsaw",
                "AN-123",
                100,
                LocalDate.of(2022,1,1),
                LocalTime.of(15,0)));
    }

    @Test
    public void findByFieldsIfFlightExist(){
        flightRepository.save(flights.get(0));
        List<Flight> flightList = flightRepository.findByFields("Kiev", "Warsaw", LocalDate.of(2022,1,1));

        assertEquals(1, flightList.size());
        flightRepository.delete(flights.get(0));
    }
    @Test
    public void findByFieldsIfFlightMoreThenOne(){
        flightRepository.save(flights.get(0));
        flightRepository.save(flights.get(1));

        List<Flight> flightList = flightRepository.findByFields("Kiev", "Warsaw", LocalDate.of(2022,1,1));
        assertEquals(2, flightList.size());

        flightRepository.delete(flights.get(0));
        flightRepository.delete(flights.get(1));
    }
    @Test
    public void findByFieldsIfFlightNotExist(){
        flightRepository.save(flights.get(0));
        List<Flight> flightList = flightRepository.findByFields("Kiev", "Paris", LocalDate.of(2022,1,1));

        assertEquals(0, flightList.size());
        flightRepository.delete(flights.get(0));
    }
}
