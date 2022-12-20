package anton.maliar.AirlineReservationSystem.service;

import anton.maliar.AirlineReservationSystem.repository.UserRepository;
import anton.maliar.AirlineReservationSystem.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean isUserExist(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");
        User user = userRepository.findByFields(name, surname, password);

        if(user != null ){
            request.setAttribute("userId", user.getId());
            return true;
        }
        return false;
    }
    public void login(HttpServletRequest request){
        Long userId = (Long) request.getAttribute("userId");
        HttpSession session = request.getSession(true);
        session.setAttribute("userId", userId);
    }
    public void saveUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");

        User user = new User(name, surname, password);
        userRepository.save(user);
    }
    public void updateUser(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");

        User user = userRepository.getReferenceById(userId);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);

        userRepository.save(user);
    }
    public boolean validateInput(HttpServletRequest request){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");

        List<String> list = Stream.of(name,surname,password).toList();

        for (String value : list){
            if(value.isEmpty() || value.length() > 20){
                return true;
            }
        }
        return false;
    }
    public User getUser(HttpServletRequest request){
        Long userId = (Long) request.getSession().getAttribute("userId");
        User user = userRepository.getReferenceById(userId);
        return user;
    }
}
