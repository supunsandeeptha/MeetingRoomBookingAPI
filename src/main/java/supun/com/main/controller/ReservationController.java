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
import supun.com.main.repository.ReservationRepository;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {

    //logging purposes
    Logger logger = LoggerFactory.getLogger(ReservationController.class);

    // injecting the reservation repository
    @Autowired
    private ReservationRepository reservationRepository;

    private Reservation reservation;

    //creating a reservation
    @PostMapping("/reservations")
    public ResponseEntity<String> createReservation(@RequestBody Employee employee, MeetingRoom meetingRoom, Reservation reservation) {

        // getting the from date and
        Date fromDate = reservation.getFromDate();
        Date toDate = reservation.getToDate();
        int id = meetingRoom.getId();
        System.out.println(id);

        List<Reservation> reservations = reservationRepository.findAll();
        for(Reservation res: reservations){
            logger.info("---------- Inside the loop of the reservations ---------");
            if(res.getMeetingRoom().getId() == id){
                logger.info("---------- Meeting Room IDs has been matched ---------");
                if(res.getFromDate() == fromDate && res.getToDate() == toDate){
                    logger.error("---------- Existing Reservation ---------");
                    return ResponseEntity.badRequest().body("Existing Reservation For the Meeting Room !");

                }else {
                    reservation.setEmployee(employee);
                    reservation.setMeetingRoom(meetingRoom);
                    reservationRepository.save(reservation);
                    logger.info("---------- Reservation Has Been Created Successfully  ---------");
                    return ResponseEntity.ok().body("Reservation Has Been Successfully Created");
                }
            }else {

                return ResponseEntity.badRequest().body("Meeting Room Not Found !");
            }
        }

        return null;
    }


    //get all the reservations
    @GetMapping("/reservations")
    public List<Reservation> getAllReservations(){
        return  reservationRepository.findAll();
    }

    // get a reservation by id
    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable(value = "id") int reservationId)
            throws ResourceNotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found for this id :: " + reservationId));
        logger.info("------ Find Reservation By ID. Reservation of Id" + reservationId + " is " + reservation);
        return ResponseEntity.ok().body(reservation);
    }


    //delete a reservation
    @DeleteMapping("/reservations/{id}")
    public Map<String, Boolean> deleteReservation(@PathVariable(value = "id") int reservationId)
            throws ResourceNotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + reservationId));

        reservationRepository.delete(reservation);
        Map<String, Boolean> response = new HashMap<>();
        logger.info("----- Reservation Cancelled Successfully ------");
        response.put("Reservation Cancelled Successfully", Boolean.TRUE);
        return response;
    }


}
