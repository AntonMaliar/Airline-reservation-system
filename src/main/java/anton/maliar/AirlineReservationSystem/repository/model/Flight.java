package anton.maliar.AirlineReservationSystem.repository.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "\"from\"")
    private String from;
    @Column(name = "\"to\"")
    private String to;
    private String plane;
    @Column(name = "number_seats")
    private int numberSeats;
    private LocalDate date;
    @Column(name = "\"time\"")
    private LocalTime time;

    @OneToMany(mappedBy = "flight")
    private List<Ticket> reserveSeats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public int getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(int numberSeats) {
        this.numberSeats = numberSeats;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<Ticket> getReserveSeats() {
        return reserveSeats;
    }

    public void setReserveSeats(List<Ticket> reserveSeats) {
        this.reserveSeats = reserveSeats;
    }

    public Flight() {
    }

    public Flight(String from, String to, String plane, int numberSeats, LocalDate date, LocalTime time) {
        this.from = from;
        this.to = to;
        this.plane = plane;
        this.numberSeats = numberSeats;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", plane='" + plane + '\'' +
                ", numberSeats=" + numberSeats +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}