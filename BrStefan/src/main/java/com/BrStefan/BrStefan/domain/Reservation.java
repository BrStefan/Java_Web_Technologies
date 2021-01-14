package com.BrStefan.BrStefan.domain;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private String id;
    private String owner;
    private int number_of_guests;
    private Date reservation_date;
    private boolean confirmed;
    private boolean honored;
    private int table;

}
