package supun.com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supun.com.main.model.MeetingRoom;

@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom,Integer> {
}
