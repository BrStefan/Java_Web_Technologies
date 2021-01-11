package com.BrStefan.BrStefan.controller;


import com.BrStefan.BrStefan.domain.Reservation;
import com.BrStefan.BrStefan.exceptions.StaffException;
import com.BrStefan.BrStefan.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<Object>getReservations(@RequestBody int user_id){
        try{
            List<Reservation> reservations = reservationService.getReservations(user_id);
            return ResponseEntity.ok().body(reservations);
        }
        catch(StaffException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
