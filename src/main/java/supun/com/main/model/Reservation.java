package supun.com.main.model;


import lombok.*;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

import java.sql.Date;
import java.util.Objects;

//@Getter
//@Setter
//@NoArgsConstructor
@Data

@Entity
//@IdClass(EmployeeId.class)
// class for reservations implementing the serializable interface
public class Reservation implements Serializable {

    @Id
    @GeneratedValue()
    private int id;
    // mapping the meeting room (PK)
   // @Id
    @ManyToOne
    private MeetingRoom meetingRoom;

    // mapping the employee (PK)
    //@Id
    @ManyToOne
    private Employee employee;

    //reservation title
    //@NotEmpty(message = "Please Provide a Reservation Title")
    private String reservationTitle;

    //book from date
    //@NotEmpty(message = "Please Provide a Reservation Starting Date")
    private Date fromDate;

    // book to date
    //@NotEmpty(message = "Please Provide a Reservation End Date")
    private Date toDate;

    //constructor
    public Reservation(String reservationTitle, Employee employee,MeetingRoom meetingRoom, Date fromDate, Date toDate) {
        this.reservationTitle = reservationTitle;
        this.employee = employee;
        this.meetingRoom = meetingRoom;
        this.toDate = toDate;
        this.fromDate = fromDate;
    }

    // overriding the object equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(meetingRoom.getName(), that.meetingRoom.getName()) &&
                Objects.equals(employee.getName(), that.employee.getName()) &&
                Objects.equals(fromDate, that.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingRoom.getName(), employee.getName(), fromDate);
    }

    // getters and setters
    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getReservationTitle() {
        return reservationTitle;
    }

    public void setReservationTitle(String reservationTitle) {
        this.reservationTitle = reservationTitle;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
