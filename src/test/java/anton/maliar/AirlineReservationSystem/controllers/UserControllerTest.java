package anton.maliar.AirlineReservationSystem.controllers;

import anton.maliar.AirlineReservationSystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {
    private UserController userController;
    private UserService userService;
    private HttpServletRequest request;
    private Model model;

    @BeforeEach
    public void init(){
        userService = mock(UserService.class);
        userController = new UserController(userService);
        request = mock(HttpServletRequest.class);
        model = mock(Model.class);
    }

    @Test
    public void loginIfUserExist(){
        when(userService.isUserExist(request)).thenReturn(true);

        assertEquals("redirect:/user/account", userController.login(request));
        verify(userService).login(request);
    }
    @Test
    public void loginIfUserNotExist(){
        assertEquals("redirect:/?errorMessage=errorMessage", userController.login(request));
    }
    @Test
    public void logoutTest(){
        when(request.getSession(false)).thenReturn(mock(HttpSession.class));

        assertEquals("redirect:/", userController.logout(request));
        verify(request.getSession(false)).invalidate();
    }
    @Test
    public void userAccountIfSessionExist(){
        when(request.getSession(false)).thenReturn(mock(HttpSession.class));

        assertEquals("user/account", userController.userAccount(request, model));
        verify(model).addAttribute("user", userService.getUser(request));
    }
    @Test
    public void userAccountIfSessionNotExist(){
        assertEquals("redirect:/?errorMessage=errorMessage", userController.userAccount(request, model));
    }
    @Test
    public void createUserIfInputNotCorrect(){
        when(userService.validateInput(request)).thenReturn(true);

        assertEquals("user/login-registration", userController.createUser(request, model));
        verify(model).addAttribute(
                "errorMessage",
                "The fields must not be empty and their size must not exceed 20 symbols"
        );
    }
    @Test
    public void createUserIfUserExist(){
        when(userService.isUserExist(request)).thenReturn(true);

        assertEquals("user/login-registration", userController.createUser(request, model));
        verify(model).addAttribute("errorMessage","User already exists");
    }
    @Test
    public void createUserIfValidInputAndUserNotExist(){
        assertEquals("user/login-registration", userController.createUser(request, model));
        verify(userService).saveUser(request);
        verify(model).addAttribute("errorMessage", "User created, please log in");
    }
    @Test
    public void updateUserIfSessionExist(){
        when(request.getSession(false)).thenReturn(mock(HttpSession.class));

        assertEquals("user/update", userController.updateUser(request, model));
        verify(model).addAttribute("user", userService.getUser(request));
    }
    @Test
    public void updateUserIfSessionNotExist(){
        assertEquals("redirect:/?errorMessage=errorMessage", userController.updateUser(request, model));
    }
    @Test
    public void updateUserPostIfSessionExist(){
        when(request.getSession(false)).thenReturn(mock(HttpSession.class));

        assertEquals("redirect:/user/account", userController.updateUserPost(request));
        verify(userService).updateUser(request);
    }
    @Test
    public void updateUserPostIfSessionNotExist(){
        assertEquals("redirect:/?errorMessage=errorMessage", userController.updateUserPost(request));
    }

}






































