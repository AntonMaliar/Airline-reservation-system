package anton.maliar.AirlineReservationSystem.admin;

import anton.maliar.AirlineReservationSystem.repository.AdminRepository;
import anton.maliar.AirlineReservationSystem.repository.model.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AdminServiceTest {
    private AdminService adminService;
    private AdminRepository adminRepository;
    private HttpServletRequest request;

    @BeforeEach
    public void init(){
        request = mock(HttpServletRequest.class);
        adminRepository = mock(AdminRepository.class);
        adminService = new AdminService(adminRepository);
    }
    @Test
    public void isAdminValidIfSessionExist(){
        HttpSession session = mock(HttpSession.class);
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("admin")).thenReturn("admin");

        assertTrue(adminService.isAdminValid(request));
    }
    @Test
    public void isAdminValidIfSessionNotExist(){
        when(request.getSession(false)).thenReturn(null);

        assertFalse(adminService.isAdminValid(request));
    }
    @Test
    public void isAdminExistTrue(){
        when(adminRepository.findByFields(
                        request.getParameter("name"),
                        request.getParameter("surname"),
                        request.getParameter("password")
                )
        ).thenReturn(new Admin());

        assertTrue(adminService.isAdminExist(request));
    }
    @Test
    public void isAdminExistFalse(){
        when(adminRepository.findByFields(
                        request.getParameter("name"),
                        request.getParameter("surname"),
                        request.getParameter("password")
                )
        ).thenReturn(null);

        assertFalse(adminService.isAdminExist(request));
    }
}
