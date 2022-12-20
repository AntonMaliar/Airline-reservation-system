package anton.maliar.AirlineReservationSystem.service;

import anton.maliar.AirlineReservationSystem.repository.FlightRepository;
import anton.maliar.AirlineReservationSystem.repository.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FlightService {
    private FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public List<Flight> findFlight(HttpServletRequest request) {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        LocalDate date = LocalDate.parse(request.getParameter("date"));

        return flightRepository.findByFields(from, to, date);
    }

    public Flight getFlight(HttpServletRequest request) {
        Long flightId = Long.parseLong(request.getParameter("flightId"));
        return flightRepository.getReferenceById(flightId);
    }

    public List<Integer> getFreeSeats(Flight flight){
        List<Integer> freeSeats = Stream.iterate(1, i -> i+1)
                .limit(flight.getNumberSeats())
                .collect(Collectors.toList());

        List<Integer> reserveSeats = flight.getReserveSeats().stream()
                .map(ticket -> ticket.getPlace())
                .collect(Collectors.toList());

        reserveSeats.stream()
                .forEach(integer -> freeSeats.remove(integer));
        return freeSeats;
    }

    public void addFlight(HttpServletRequest request) {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String plane = request.getParameter("plane");
        int numberSeats = Integer.parseInt(request.getParameter("number_seats"));
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime time = LocalTime.parse(request.getParameter("time"));

        flightRepository.save(new Flight(from, to, plane, numberSeats, date, time));
    }

    public void updateFlight(HttpServletRequest request){
        Long flightId = (Long) request.getSession().getAttribute("flightId");
        Flight flight = flightRepository.getReferenceById(flightId);

        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String plane = request.getParameter("plane");
        int numberSeats = Integer.parseInt(request.getParameter("number_seats"));
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime time = LocalTime.parse(request.getParameter("time"));

        flight.setFrom(from);
        flight.setTo(to);
        flight.setPlane(plane);
        flight.setNumberSeats(numberSeats);
        flight.setDate(date);
        flight.setTime(time);

        flightRepository.save(flight);
        request.getSession().removeAttribute("flightId");
    }

    public void deleteFlight(HttpServletRequest request) {
        Long flightId = Long.parseLong(request.getParameter("flightId"));
        flightRepository.deleteById(flightId);
    }
}
