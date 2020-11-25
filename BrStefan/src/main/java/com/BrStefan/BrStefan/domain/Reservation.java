package com.BrStefan.BrStefan.domain;

import lombok.*;

import java.util.Date;

@Getter
@Builder
public class Reservation {

    private User owner;
    private int number_of_guests;
    private Date date;
    private boolean confirmed;
    private boolean honored;
    private Table table;

}
