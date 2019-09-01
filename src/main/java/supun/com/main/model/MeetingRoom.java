package supun.com.main.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Data

@Entity
// class for meeting room
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // variable for the meeting room id
    private int id;

    // variable to store the name of meeting room
    @Column(name = "name")
    @NotEmpty(message = "Please provide a meeting room name")
    private String name;

    // mapping the relationship with reservations
    @OneToMany(mappedBy = "meetingRoom", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

    // constructor
    public MeetingRoom(String name) {
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
