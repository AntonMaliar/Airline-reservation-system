package anton.maliar.AirlineReservationSystem.repository;

import anton.maliar.AirlineReservationSystem.repository.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query(value = "SELECT * FROM flights " +
            "WHERE \"from\" = :from " +
            "AND \"to\" = :to " +
            "AND date = :date", nativeQuery = true)
    List<Flight> findByFields(
            @Param("from") String from,
            @Param("to") String to,
            @Param("date") LocalDate date
    );
}
