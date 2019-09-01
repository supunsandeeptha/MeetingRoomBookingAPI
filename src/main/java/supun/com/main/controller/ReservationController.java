package supun.com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supun.com.main.model.Reservation;
import supun.com.main.repository.ReservationRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {


    // injecting the reservation repository
    @Autowired
    private ReservationRepository reservationRepository;




    //creating a reservation
    @PostMapping("/reservations")
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {

        Date date = reservation.getFromDate();
        Date date1 = reservation.getToDate();
        int id = reservation.getMeetingRoom().getId();
        System.out.println(id);

        List<Reservation> reservations = reservationRepository.findAll();
        for(Reservation res: reservations){
            if(res.getMeetingRoom().getId() == id){
                if(res.getFromDate() == date && res.getToDate() == date1){

                    return ResponseEntity.badRequest().body("Existing Reservation For the Meeting Room !");

                }else {
                    reservationRepository.save(reservation);
                    return ResponseEntity.ok().body("Reservation Has Been Successfully Created");
                }
            }else {

                return ResponseEntity.badRequest().body("Meeting Room Not Found !");
            }
        }

        return null;
    }





    //get all the employees
    @GetMapping("/reservations")
    public List<Reservation> getAllReservations(){
        return  reservationRepository.findAll();
    }
}
