package com.BrStefan.BrStefan.domain;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String username;
    private String pass;
    private String full_name;
    private Integer role;
    private Reservation active_reservation;
    private List<Reservation> older_reservations;
}
