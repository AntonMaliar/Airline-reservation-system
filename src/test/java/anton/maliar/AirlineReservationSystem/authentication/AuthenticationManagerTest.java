package anton.maliar.AirlineReservationSystem.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.HttpSession;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AuthenticationManagerTest {
    private AuthenticationManager manager;

    @BeforeEach
    public void initAuthenticationManager(){
        manager = new AuthenticationManager();
    }
    @Test
    public void addSessionTest(){
        HttpSession session = Mockito.mock(HttpSession.class);
        assertTrue(manager.addSession(session));
    }
    @Test
    public void isSessionValidTest(){
        HttpSession session = Mockito.mock(HttpSession.class);
        when(session.getId()).thenReturn("1");
        manager.addSession(session);

        try {
            assertTrue(manager.isSessionValid(session));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void ifManagerDontHaveSession(){
        HttpSession session = Mockito.mock(HttpSession.class);
        HttpSession session1 = Mockito.mock(HttpSession.class);
        when(session.getId()).thenReturn("1");
        when(session1.getId()).thenReturn("2");

        manager.addSession(session);

        assertFalse(manager.isSessionValid(session1));
    }
}
