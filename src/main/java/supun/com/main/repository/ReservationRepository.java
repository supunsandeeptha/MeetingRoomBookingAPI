package supun.com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supun.com.main.model.MeetingRoom;
import supun.com.main.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
}
