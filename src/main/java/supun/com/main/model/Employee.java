package supun.com.main.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data

@Entity
//employee class
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // employee id (auto generated)
     int id;

    // employee name
    @NotEmpty(message = "Please provide a employee name")
    private String name;

    // mapping with the reservations
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    public Employee(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
