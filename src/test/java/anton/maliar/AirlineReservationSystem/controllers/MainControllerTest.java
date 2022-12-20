package anton.maliar.AirlineReservationSystem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MainControllerTest {
    private MainController mainController;
    private HttpServletRequest request;
    private Model model;

    @BeforeEach
    public void init(){
        mainController = new MainController();
        request = mock(HttpServletRequest.class);
        model = mock(Model.class);
    }

    @Test
    public void welcomePageIfSessionExists(){
        when(request.getSession(false)).thenReturn(mock(HttpSession.class));

        assertEquals("redirect:/user/account", mainController.welcomePage(request, model));
    }
    @Test
    public void welcomePageIfRedirectFromAnotherEndPointWithErrorParameter(){
        when(request.getParameter("errorMessage")).thenReturn("errorMessage");

        assertEquals("user/login-registration", mainController.welcomePage(request, model));
        verify(model).addAttribute("errorMessage", "Please log in");
    }
    @Test
    public void welcomePageIfSessionNotExist(){
        assertEquals("user/login-registration", mainController.welcomePage(request, model));
        verify(model, never()).addAttribute("errorMessage", "Please log in");
    }
}





















