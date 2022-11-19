package anton.maliar.AirlineReservationSystem.authentication;

import anton.maliar.AirlineReservationSystem.repository.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Authentication {

    private HttpSession session;


    @GetMapping("/")
    public String welcomePage(HttpServletRequest request) {
        //when we visit the site and the session is valid for the user, we redirect him to his page
        if(session != null){
            return "user-account";
        }
        return "login-registration";
    }

    @PostMapping("/new-user")
    public String createNewUser(HttpServletRequest request){
        //logic to save the user
        return "user-account";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model){
        //user validation logic if true create session for user and add to Authentication manager
        if(true){
            session = request.getSession();
            return "user-account";
        }
        model.addAttribute("errorMessage", "Not valid user");
        return "login-registration";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        return "login-registration";
    }

}
