package anton.maliar.AirlineReservationSystem.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Authentication {

    private AuthenticationManager manager;
    @Autowired
    public Authentication(AuthenticationManager manager){
        this.manager = manager;
    }

    @GetMapping("/")
    public String welcomePage(HttpServletRequest request) {
        //when we visit the site and the session is valid for the user, we redirect him to his page
        if(manager.isSessionValid(request.getSession())){
            return "user-account";
        }
        return "login-registration";
    }

    @PostMapping("/new-user")
    public String createNewUser(HttpServletRequest request){
        return "user-account";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model){
        //user validation logic if true create session for user and add to Authentication manager
        if(true){
            HttpSession session = request.getSession();
            manager.addSession(session);
            return "user-account";
        }
        model.addAttribute("errorMessage", "Not valid user");
        return "login-registration";
    }

}
