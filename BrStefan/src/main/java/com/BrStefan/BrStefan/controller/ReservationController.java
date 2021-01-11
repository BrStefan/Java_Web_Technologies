package com.BrStefan.BrStefan.controller;

import com.BrStefan.BrStefan.domain.Reservation;
import com.BrStefan.BrStefan.domain.dto.ConfirmReservationDTO;
import com.BrStefan.BrStefan.domain.dto.HonorDTO;
import com.BrStefan.BrStefan.domain.dto.ReservationDTO;
import com.BrStefan.BrStefan.exceptions.AddReservationException;
import com.BrStefan.BrStefan.exceptions.ConfirmReservationException;
import com.BrStefan.BrStefan.exceptions.GetReservationsException;
import com.BrStefan.BrStefan.exceptions.HonorException;
import com.BrStefan.BrStefan.service.ReservationService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    private ResponseEntity<Object>book(@RequestBody @Valid ReservationDTO reservationDTO){
        try {
            Reservation res = reservationService.book(reservationDTO);
            return ResponseEntity.ok().body(res);
        }
        catch (AddReservationException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/confirm")
    private ResponseEntity<Object>confirm(@RequestBody @Valid ConfirmReservationDTO confirmReservationDTO){
        try{
            reservationService.confirm(confirmReservationDTO);
            return ResponseEntity.ok().body("Reservation confirmed");
        }
        catch(ConfirmReservationException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @DeleteMapping("/reject")
    private ResponseEntity<Object>reject(@RequestBody @Valid ConfirmReservationDTO confirmReservationDTO){
        try{
            reservationService.reject(confirmReservationDTO);
            return ResponseEntity.ok().body("Reservation rejected");
        }
        catch(ConfirmReservationException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping()
    private ResponseEntity<Object>getForToday(@RequestBody int user_id){
        try{
            List<Reservation> reservations = reservationService.getForDay(user_id);
            return ResponseEntity.ok().body(reservations);
        }
        catch(GetReservationsException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/honor")
    private ResponseEntity<Object>honor(@RequestBody @Valid HonorDTO honorDTO){
        try{
            reservationService.honor(honorDTO);
            return ResponseEntity.ok().body("Reservation honored");
        }
        catch(HonorException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }

    }
}
