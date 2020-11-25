package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepository {

    private List<Reservation> reservations;

    public Reservation add(Reservation reservation){
        reservations.add(reservation);
        return  reservation;
    }

    public List<Reservation> getAll(){
        return reservations;
    }

    public Reservation honor(Reservation reservation){
        //reservation.honored = True;
        return reservation;
    }

    public Reservation confirm(Reservation reservation){
        //reservation.confirmed = True;
        return reservation;
    }


}

