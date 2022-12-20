package anton.maliar.AirlineReservationSystem.admin;

import anton.maliar.AirlineReservationSystem.repository.AdminRepository;
import anton.maliar.AirlineReservationSystem.repository.model.Admin;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
public class AdminService {
    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public boolean isAdminValid(HttpServletRequest request){
        if(request.getSession(false)!= null && request.getSession(false).getAttribute("admin").equals("admin")){
            return true;
        }
        return false;
    }

    public boolean isAdminExist(HttpServletRequest request) {
        Admin admin = adminRepository.findByFields(
                request.getParameter("name"),
                request.getParameter("surname"),
                request.getParameter("password")
        );

        if(admin != null){
            return true;
        }
        return false;
    }
}
