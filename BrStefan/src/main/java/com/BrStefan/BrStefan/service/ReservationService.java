package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.Reservation;
import com.BrStefan.BrStefan.domain.dto.ConfirmReservationDTO;
import com.BrStefan.BrStefan.domain.dto.HonorDTO;
import com.BrStefan.BrStefan.domain.dto.ReservationDTO;
import com.BrStefan.BrStefan.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation book(ReservationDTO reservationDTO){
        return reservationRepository.add(reservationDTO);
    }

    public void confirm(ConfirmReservationDTO confirmReservationDTO){
        reservationRepository.confirm(confirmReservationDTO);
    }

    public void reject(ConfirmReservationDTO confirmReservationDTO){
        reservationRepository.reject(confirmReservationDTO);
    }

    public List<Reservation> getForDay(int user_id){
        return reservationRepository.getForDay(user_id);
    }

    public void honor(HonorDTO honorDTO){
        reservationRepository.honor(honorDTO);
    }

    public List<Reservation> getReservations(int user_id){
        return reservationRepository.getAll(user_id);
    }
}
