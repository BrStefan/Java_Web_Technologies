package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.Reservation;
import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.*;
import com.BrStefan.BrStefan.repository.ReservationRepository;
import com.BrStefan.BrStefan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;


    @Test
    void testBook(){

        ReservationDTO reservationDTO = new ReservationDTO("1",new Date(2020, Calendar.APRIL,5), 3,4);

        Reservation reservation = new Reservation("1","1", 3, new Date(2020, Calendar.APRIL,5), false,false,4);
        when(reservationRepository.add(reservationDTO)).thenReturn(reservation);

        Reservation result = reservationService.book(reservationDTO);
        assertThat(result.getOwner()).isEqualTo(reservation.getOwner());
    }

    @Test
    void testBookFail(){

        ReservationDTO reservationDTO = new ReservationDTO("1",new Date(2020, Calendar.APRIL,5), 3,4);

        when(reservationRepository.add(reservationDTO)).thenReturn(null);
        Reservation result = reservationService.book(reservationDTO);
        assertThat(result).isEqualTo(null);

    }

    @Test
    void testConfirm(){

        ConfirmReservationDTO reservationDTO = new ConfirmReservationDTO("1","1");
        Reservation reservation = new Reservation("1","1", 3, new Date(2020, Calendar.APRIL,5), true,false,4);
        reservationService.confirm(reservationDTO);
        assertThat(reservation.isConfirmed()).isEqualTo(true);
    }

    @Test
    void testConfirmFail(){

        ConfirmReservationDTO reservationDTO = new ConfirmReservationDTO("1","1");
        Reservation reservation = new Reservation("1","1", 3, new Date(2020, Calendar.APRIL,5), false,false,4);
        reservationService.confirm(reservationDTO);
        assertThat(reservation.isConfirmed()).isEqualTo(false);
    }

    @Test
    void testReject(){

        ConfirmReservationDTO reservationDTO = new ConfirmReservationDTO("1","1");
        Reservation reservation = new Reservation("1","1", 3, new Date(2020, Calendar.APRIL,5), false,false,4);
        reservationService.confirm(reservationDTO);
        assertThat(reservation.isConfirmed()).isEqualTo(true);
    }

    @Test
    void testGetForToday(){
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(new Reservation("1","1", 3, new Date(2020, Calendar.APRIL,5), false,false,4));
        reservationList.add(new Reservation("1","2", 3, new Date(2020, Calendar.APRIL,5), false,false,4));
        reservationList.add(new Reservation("1","1", 3, new Date(2020, Calendar.APRIL,5), false,false,4));

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation("1","1", 3, new Date(2020, Calendar.APRIL,5), false,false,4));
        reservations.add(new Reservation("1","1", 3, new Date(2020, Calendar.APRIL,5), false,false,4));
        when(reservationService.getForDay(1)).thenReturn(reservations);

        List<Reservation> res = reservationService.getForDay(1);
        assertThat(reservationList.get(0).getOwner()).isEqualTo(res.get(0).getOwner());
        assertThat(reservationList.get(2).getOwner()).isEqualTo(res.get(1).getOwner());
    }

    @Test
    void testHonor(){
        HonorDTO honorDTO = new HonorDTO("1", "2");
        Reservation reservation = new Reservation("1","1", 3, new Date(2020, Calendar.APRIL,5), false,true,4);

        reservationService.honor(honorDTO);
        assertThat(reservation.isHonored()).isEqualTo(true);
    }

    @Test
    void testGetForUser(){
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(new Reservation("1","2", 3, new Date(2020, Calendar.APRIL,5), false,false,4));
        reservationList.add(new Reservation("2","2", 3, new Date(2020, Calendar.APRIL,5), false,false,4));
        reservationList.add(new Reservation("3","1", 3, new Date(2020, Calendar.APRIL,5), false,false,4));

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation("1","2", 3, new Date(2020, Calendar.APRIL,5), false,false,4));
        reservations.add(new Reservation("2","2", 3, new Date(2020, Calendar.APRIL,5), false,false,4));
        when(reservationService.getReservations(1)).thenReturn(reservations);

        List<Reservation> res = reservationService.getReservations(1);

        assertThat(reservationList.get(0).getOwner()).isEqualTo(res.get(0).getOwner());
        assertThat(reservationList.get(1).getOwner()).isEqualTo(res.get(1).getOwner());
    }





}