package anton.maliar.AirlineReservationSystem.admin;

import anton.maliar.AirlineReservationSystem.repository.model.Flight;
import anton.maliar.AirlineReservationSystem.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    private FlightService flightService;

    @Autowired
    public AdminController(AdminService adminService, FlightService flightService){
        this.adminService = adminService;
        this.flightService = flightService;
    }

    @GetMapping()
    public String adminLogin(HttpServletRequest request, Model model){
        if(request.getParameter("errorMessage") != null){
            model.addAttribute("errorMessage", "Please log in");
            return "admin/login";
        }
        return "admin/login";
    }

    @GetMapping("/account")
    public String adminAccount(HttpServletRequest request){
        if(adminService.isAdminValid(request)){
            return "admin/account";
        }
        return "redirect:/admin?errorMessage=errorMessage";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request){
        if(adminService.isAdminExist(request)){
            request.getSession().setAttribute("admin", "admin");
            return "redirect:/admin/account";
        }
        return "redirect:/admin?errorMessage=errorMessage";
    }

    @PostMapping("/add-flight")
    public String addFlight(HttpServletRequest request){
        if(adminService.isAdminValid(request)){
            flightService.addFlight(request);
            return "redirect:/admin/account";
        }
        return "redirect:/admin?errorMessage=errorMessage";
    }

    @GetMapping("/find-flight")
    public String findFlight(HttpServletRequest request, Model model) {
        if(adminService.isAdminValid(request)){
            model.addAttribute("flights", flightService.findFlight(request));
            return "admin/find-flight";
        }
        return "redirect:/admin?errorMessage=errorMessage";
    }

    @GetMapping("/update-flight")
    public String updateFlight(HttpServletRequest request, Model model){
        if(adminService.isAdminValid(request)){
            Flight flight = flightService.getFlight(request);
            request.getSession().setAttribute("flightId", flight.getId());
            model.addAttribute("flight" ,flight);
            return "admin/update-flight";
        }
        return "redirect:/admin?errorMessage=errorMessage";
    }

    @PostMapping("/update-flight")
    public String updateFlightPost(HttpServletRequest request){
        if(adminService.isAdminValid(request)){
            flightService.updateFlight(request);
            return "redirect:/admin/account";
        }
        return "redirect:/admin?errorMessage=errorMessage";
    }

    @GetMapping("/delete-flight")
    public String deleteFlight(HttpServletRequest request){
        if(adminService.isAdminValid(request)){
            flightService.deleteFlight(request);
            return "redirect:/admin/account";
        }
        return "redirect:/admin?errorMessage=errorMessage";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/admin";
    }
}
