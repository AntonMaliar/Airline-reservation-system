package anton.maliar.AirlineReservationSystem.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Authentication {
    @GetMapping("/")
    public String welcomePage(HttpServletRequest request){
//        if(request.getSession() != null){
//            return "user-account";
//        }
        return "login-registration";
    }
}
