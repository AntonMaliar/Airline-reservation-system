package anton.maliar.AirlineReservationSystem.repository;

import anton.maliar.AirlineReservationSystem.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM users " +
            "WHERE name = :name " +
            "AND surname = :surname " +
            "AND password = :password", nativeQuery = true)
    User findByFields(
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("password") String password
    );

}
