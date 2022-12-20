package anton.maliar.AirlineReservationSystem.controllers;

import anton.maliar.AirlineReservationSystem.repository.model.Flight;
import anton.maliar.AirlineReservationSystem.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FlightControllerTest {
    private FlightController flightController;
    private FlightService flightService;
    private HttpServletRequest request;
    private Model model;

    @BeforeEach
    public void init(){
        flightService = mock(FlightService.class);
        flightController = new FlightController(flightService);
        request = mock(HttpServletRequest.class);
        model = mock(Model.class);
    }
    @Test
    public void findFlightIfSessionExist(){
        when(request.getSession(false)).thenReturn(mock(HttpSession.class));

        assertEquals("flight/find", flightController.findFlight(request));
    }
    @Test
    public void findFlightIfSessionNotExist(){
        assertEquals("redirect:/?errorMessage=errorMessage", flightController.findFlight(request));
    }
    @Test
    public void getFlightIfSessionExist(){
        when(request.getSession(false)).thenReturn(mock(HttpSession.class));

        Flight flight = mock(Flight.class);
        List<Integer> freeSeats = mock(List.class);
        when(flightService.getFlight(request)).thenReturn(flight);
        when(flightService.getFreeSeats(flight)).thenReturn(freeSeats);

        assertEquals("flight/get", flightController.getFlight(request, model));
        verify(flightService).getFlight(request);
        verify(flightService).getFreeSeats(flight);
        verify(model).addAttribute("flight", flight);
        verify(model).addAttribute("freeSeats", freeSeats);
    }
    @Test void getFlightIfSessionNotExist(){
        assertEquals("redirect:/?errorMessage=errorMessage", flightController.getFlight(request, model));
    }
    @Test
    public void findFlightPostIfSessionExist(){
        when(request.getSession(false)).thenReturn(mock(HttpSession.class));

        assertEquals("flight/find", flightController.findFlightPost(request, model));
    }
    @Test
    public void findFlightPostIfSessionNotExist(){
        assertEquals("redirect:/?errorMessage=errorMessage", flightController.findFlightPost(request, model));
    }
}





















