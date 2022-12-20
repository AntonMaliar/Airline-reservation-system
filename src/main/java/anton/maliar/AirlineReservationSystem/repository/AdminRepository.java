package anton.maliar.AirlineReservationSystem.repository;

import anton.maliar.AirlineReservationSystem.repository.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query(value = "SELECT * FROM admin " +
            "WHERE name = :name " +
            "AND surname = :surname " +
            "AND password = :password", nativeQuery = true)
    Admin findByFields(
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("password") String password
    );
}
