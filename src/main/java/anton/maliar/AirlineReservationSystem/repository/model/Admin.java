package anton.maliar.AirlineReservationSystem.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String password;

    public Admin() {
    }

    public Admin(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
}
