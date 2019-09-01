package supun.com.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supun.com.main.model.MeetingRoom;
import supun.com.main.repository.MeetingRoomRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MeetingController {

    //logging purposes
    Logger logger = LoggerFactory.getLogger(MeetingController.class);

    //Injecting the Meeting Repository
    @Autowired
    private MeetingRoomRepository meetingRoomRepository;


    //creating a meeting room
    @PostMapping("/meetingroom")
    public ResponseEntity<MeetingRoom> createMeetingRoom(@Valid @RequestBody MeetingRoom meetingRoom) {
        logger.info("------ Meeting Room Creation Method." + meetingRoom + " has been created");
         meetingRoomRepository.save(meetingRoom);
        return ResponseEntity.ok(meetingRoom);
    }


    //get all the employees
    @GetMapping("/meetingrooms")
    public List<MeetingRoom> getAllRooms(){
        logger.info("------- Method to find the meeting rooms -------");
        return meetingRoomRepository.findAll();
    }

}
