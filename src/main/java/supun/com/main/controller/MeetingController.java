package supun.com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import supun.com.main.model.MeetingRoom;
import supun.com.main.repository.MeetingRoomRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MeetingController {

    //Injecting the Meeting Repository
    @Autowired
    private MeetingRoomRepository meetingRoomRepository;


    //creating a meeting room
    @PostMapping("/meetingroom")
    public MeetingRoom createMeetingRoom(@Valid @RequestBody MeetingRoom meetingRoom) {
        return meetingRoomRepository.save(meetingRoom);
    }


    //get all the employees
    @GetMapping("/meetings")
    public List<MeetingRoom> getAllRooms(){
        return  meetingRoomRepository.findAll();
    }

}
