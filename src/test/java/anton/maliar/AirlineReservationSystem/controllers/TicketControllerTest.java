package anton.maliar.AirlineReservationSystem.controllers;

import anton.maliar.AirlineReservationSystem.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TicketControllerTest {
    private TicketController ticketController;
    private TicketService ticketService;
    private HttpServletRequest request;

    @BeforeEach
    public void init(){
        ticketService = mock(TicketService.class);
        ticketController = new TicketController(ticketService);
        request = mock(HttpServletRequest.class);
    }
    @Test
    public void reserveTicketIfSessionExist(){
        when(request.getSession(false)).thenReturn(mock(HttpSession.class));

        assertEquals("redirect:/user/account", ticketController.reserveTicket(request));
        verify(ticketService).reserveTicket(request);
    }
    @Test
    public void reserveTicketIfSessionNotExist(){
        assertEquals("redirect:/?errorMessage=errorMessage", ticketController.reserveTicket(request));
    }
    @Test
    public void cancelReservationIfSessionExist(){
        when(request.getSession(false)).thenReturn(mock(HttpSession.class));

        assertEquals("redirect:/user/account", ticketController.cancelReservation(request));
        verify(ticketService).cancelReservation(request);
    }
    @Test
    public void cancelReservationIfSessionNotExist(){
        assertEquals("redirect:/?errorMessage=errorMessage", ticketController.cancelReservation(request));
    }
}
