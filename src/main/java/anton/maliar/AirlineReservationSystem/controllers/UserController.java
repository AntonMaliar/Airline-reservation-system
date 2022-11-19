package anton.maliar.AirlineReservationSystem.controllers;

import anton.maliar.AirlineReservationSystem.repository.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private UserDao dao;

    @Autowired
    public UserController(UserDao dao){
        this.dao = dao;
    }

    @PostMapping("/new-user")
    public String createNewUser(HttpServletRequest request){
        dao.saveUser(request);
        return "user-account";
    }
}
