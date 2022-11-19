package anton.maliar.AirlineReservationSystem.repository.dao;

import anton.maliar.AirlineReservationSystem.repository.UserRepository;
import anton.maliar.AirlineReservationSystem.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
public class UserDao {
    private final UserRepository repository;

    @Autowired
    public UserDao(UserRepository repository){
        this.repository = repository;
    }

    public void saveUser(HttpServletRequest request){
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setPassword(request.getParameter("password"));

        repository.save(user);
    }
}
