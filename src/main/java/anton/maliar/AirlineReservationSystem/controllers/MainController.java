package anton.maliar.AirlineReservationSystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;


@Controller
public class MainController {

    @GetMapping("/")
    public String welcomePage(HttpServletRequest request, Model model) {
        if(request.getSession(false) != null){
            return "redirect:/user/account";
        }
        if(request.getParameter("errorMessage") != null){
            model.addAttribute("errorMessage", "Please log in");
            return "user/login-registration";
        }
        return "user/login-registration";
    }

}
