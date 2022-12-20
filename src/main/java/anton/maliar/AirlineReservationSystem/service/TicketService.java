package anton.maliar.AirlineReservationSystem.service;

import anton.maliar.AirlineReservationSystem.repository.FlightRepository;
import anton.maliar.AirlineReservationSystem.repository.TicketRepository;
import anton.maliar.AirlineReservationSystem.repository.UserRepository;
import anton.maliar.AirlineReservationSystem.repository.model.Flight;
import anton.maliar.AirlineReservationSystem.repository.model.Ticket;
import anton.maliar.AirlineReservationSystem.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private FlightRepository flightRepository;
    private UserRepository userRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, FlightRepository flightRepository, UserRepository userRepository){
        this.ticketRepository =  ticketRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    public void reserveTicket(HttpServletRequest request){
        Long flightId = Long.parseLong(request.getParameter("flightId"));
        int place = Integer.parseInt(request.getParameter("place"));
        Long userId = (Long) request.getSession().getAttribute("userId");

        Flight flight = flightRepository.getReferenceById(flightId);
        User user = userRepository.getReferenceById(userId);

        Ticket ticket = new Ticket(flight, user, place);
        ticketRepository.save(ticket);
    }

    public void cancelReservation(HttpServletRequest request) {
        Long ticketId = Long.parseLong(request.getParameter("ticketId"));
        ticketRepository.deleteById(ticketId);
    }
}
