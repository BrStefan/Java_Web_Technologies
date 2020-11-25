package com.BrStefan.BrStefan.domain;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class User {

    private String username;
    private String pass;
    private String full_name;
    private boolean role;
    private Reservation active_reservation;
    private List<Reservation> older_reservations;
}
