package supun.com.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supun.com.main.exception.ResourceNotFoundException;
import supun.com.main.model.Employee;
import supun.com.main.model.MeetingRoom;
import supun.com.main.model.Reservation;
import supun.com.main.repository.MeetingRoomRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    //get all the meeting rooms
    @GetMapping("/meetingrooms")
    public List<MeetingRoom> getAllRooms(){
        logger.info("------- Method to find the meeting rooms -------");
        return meetingRoomRepository.findAll();
    }


    //get meeting room by id
    @GetMapping("/meetingrooms/{id}")
    public ResponseEntity<MeetingRoom> getMeetingRoomById(@PathVariable(value = "id") int meetingRoomId)
            throws ResourceNotFoundException {
        MeetingRoom meetingRoom = meetingRoomRepository.findById(meetingRoomId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + meetingRoomId));
        logger.info("------ Find Meeting Room By ID. Meeting Room of Id" + meetingRoomId + " is " + meetingRoom);
        return ResponseEntity.ok().body(meetingRoom);
    }

    //delete a meeting room
    @DeleteMapping("/meetingrooms/{id}")
    public Map<String, Boolean> deleteMeetingRoom(@PathVariable(value = "id") int meetingRoomId)
            throws ResourceNotFoundException {
        MeetingRoom meetingRoom = meetingRoomRepository.findById(meetingRoomId)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting Room not found for this id :: " + meetingRoomId));

        meetingRoomRepository.delete(meetingRoom);
        Map<String, Boolean> response = new HashMap<>();
        logger.info("----- Meeting Room Deleted Successfully ------");
        response.put("Meeting Room Deleted Successfully", Boolean.TRUE);
        return response;
    }
}
