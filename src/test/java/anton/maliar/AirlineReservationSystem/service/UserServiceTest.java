package anton.maliar.AirlineReservationSystem.service;

import anton.maliar.AirlineReservationSystem.repository.UserRepository;
import anton.maliar.AirlineReservationSystem.repository.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;
    private HttpServletRequest request;

    @BeforeEach
    private void init(){
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
        request = mock(HttpServletRequest.class);
    }
    @Test
    public void isUserExistTrue(){
        when(userRepository.findByFields(
                request.getParameter("name"),
                request.getParameter("surname"),
                request.getParameter("password")
        )).thenReturn(mock(User.class));

        assertTrue(userService.isUserExist(request));
    }
    @Test
    public void isUserExistFalse(){
        when(userRepository.findByFields(
                request.getParameter("name"),
                request.getParameter("surname"),
                request.getParameter("password")
        )).thenReturn(null);

        assertFalse(userService.isUserExist(request));
    }
    @Test
    public void validateInputIfInputCorrect(){
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("surname")).thenReturn("surname");
        when(request.getParameter("password")).thenReturn("password");

        assertFalse(userService.validateInput(request));
    }
    @Test
    public void validateInputIfSomeFieldIsEmpty(){
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("surname")).thenReturn("surname");
        when(request.getParameter("password")).thenReturn("password");

        assertTrue(userService.validateInput(request));
    }
    @Test
    public void validateInputIfSomeFieldIsMoreThen21Characters(){
        when(request.getParameter("name")).thenReturn("123456789123456789123");
        when(request.getParameter("surname")).thenReturn("surname");
        when(request.getParameter("password")).thenReturn("password");

        assertTrue(userService.validateInput(request));
    }
}
































