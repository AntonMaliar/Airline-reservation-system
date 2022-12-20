package anton.maliar.AirlineReservationSystem.admin;

import anton.maliar.AirlineReservationSystem.repository.model.Flight;
import anton.maliar.AirlineReservationSystem.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminControllerTest {
    private AdminController adminController;
    private HttpServletRequest request;
    private Model model;
    private AdminService adminService;
    private FlightService flightService;

    @BeforeEach
    public void init(){
        adminService = Mockito.mock(AdminService.class);
        flightService = Mockito.mock(FlightService.class);

        adminController = new AdminController(adminService, flightService);
        request = Mockito.mock(HttpServletRequest.class);
        model = Mockito.mock(Model.class);
    }
    @Test
    public void adminPageWhenExistParameterInRequest(){
        when(request.getParameter("errorMessage")).thenReturn("errorMessage");
        String result = adminController.adminLogin(request, model);

        verify(model).addAttribute("errorMessage", "Please log in");
        assertEquals("admin/login", result);
    }
    @Test
    public void adminPageIfNoParameterInRequest(){
        String result = adminController.adminLogin(request, model);

        verify(model, never()).addAttribute("errorMessage", "Please log in");
        assertEquals("admin/login", result);
    }
    @Test
    public void adminAccountIfAdminValid(){
        when(adminService.isAdminValid(request)).thenReturn(true);

       assertEquals("admin/account", adminController.adminAccount(request));
    }
    @Test
    public void adminAccountIfAdminNotValid(){
        when(adminService.isAdminValid(request)).thenReturn(false);

        assertEquals("redirect:/admin?errorMessage=errorMessage", adminController.adminAccount(request));
    }
    @Test
    public void loginIfAdminExist(){
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(adminService.isAdminExist(request)).thenReturn(true);

        assertEquals("redirect:/admin/account", adminController.login(request));
        verify(session).setAttribute("admin", "admin");
    }
    @Test
    public void loginIfAdminNotExist(){
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(adminService.isAdminExist(request)).thenReturn(false);

        assertEquals("redirect:/admin?errorMessage=errorMessage", adminController.login(request));
        verify(session, never()).setAttribute("admin", "admin");
    }
    @Test
    public void addFlightIfAdminValid(){
        when(adminService.isAdminValid(request)).thenReturn(true);

        assertEquals("redirect:/admin/account", adminController.addFlight(request));
        verify(flightService).addFlight(request);
    }
    @Test
    public void addFlightIfAdminNotValid(){
        when(adminService.isAdminValid(request)).thenReturn(false);

        assertEquals("redirect:/admin?errorMessage=errorMessage", adminController.addFlight(request));
        verify(flightService, never()).addFlight(request);
    }
    @Test
    public void findFlightIfAdminValid(){
        when(adminService.isAdminValid(request)).thenReturn(true);

        assertEquals("admin/find-flight", adminController.findFlight(request, model));
        verify(flightService).findFlight(request);
        verify(model).addAttribute("flights", flightService.findFlight(request));
    }
    @Test
    public void findFlightIfAdminNotValid(){
        when(adminService.isAdminValid(request)).thenReturn(false);

        assertEquals("redirect:/admin?errorMessage=errorMessage", adminController.findFlight(request, model));
        verify(flightService, never()).findFlight(request);
        verify(model, never()).addAttribute("flights", flightService.findFlight(request));
    }
    @Test
    public void updateFlightIfAdminValid(){
        HttpSession session = mock(HttpSession.class);
        when(adminService.isAdminValid(request)).thenReturn(true);
        when(request.getSession()).thenReturn(session);
        when(flightService.getFlight(request)).thenReturn(mock(Flight.class));

        assertEquals("admin/update-flight", adminController.updateFlight(request, model));
        verify(flightService).getFlight(request);
        verify(session).setAttribute("flightId", flightService.getFlight(request).getId());
        verify(model).addAttribute("flight", flightService.getFlight(request));
    }
    @Test
    public void updateFlightIfAdminNotValid(){
        when(adminService.isAdminValid(request)).thenReturn(false);

        assertEquals("redirect:/admin?errorMessage=errorMessage", adminController.updateFlight(request, model));
    }
    @Test
    public void updateFlightPostIfAdminValid(){
        when(adminService.isAdminValid(request)).thenReturn(true);

        assertEquals("redirect:/admin/account", adminController.updateFlightPost(request));
        verify(flightService).updateFlight(request);
    }
    @Test
    public void updateFlightPostIfAdminNotValid(){
        when(adminService.isAdminValid(request)).thenReturn(false);

        assertEquals("redirect:/admin?errorMessage=errorMessage", adminController.updateFlightPost(request));
    }
    @Test
    public void deleteFlightIfAdminValid(){
        when(adminService.isAdminValid(request)).thenReturn(true);

       assertEquals("redirect:/admin/account", adminController.deleteFlight(request));
       verify(flightService).deleteFlight(request);
    }
    @Test
    public void deleteFlightIfAdminNotValid(){
        when(adminService.isAdminValid(request)).thenReturn(false);

        assertEquals("redirect:/admin?errorMessage=errorMessage", adminController.deleteFlight(request));
    }
    @Test
    public void logoutTest(){
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        assertEquals("redirect:/admin", adminController.logout(request));
        verify(session).invalidate();
    }
}









































