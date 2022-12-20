package anton.maliar.AirlineReservationSystem.repository;

import anton.maliar.AirlineReservationSystem.repository.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {
    private UserRepository userRepository;
    private User user;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void init(){
        user = new User("name", "surname", "password");
        userRepository.save(user);
    }
    @AfterEach
    public void destroy(){
        userRepository.delete(user);
    }

    @Test
    public void findByFieldsIfUserExist(){
        assertNotNull(userRepository.findByFields("name", "surname", "password"));
    }
    @Test
    public void findByFieldsIfUserNotExist(){
        assertNull(userRepository.findByFields("name1", "surname", "password"));
    }
}





















