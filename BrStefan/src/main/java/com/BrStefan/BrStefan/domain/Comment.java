package com.BrStefan.BrStefan.domain;

import lombok.*;

@Getter
@Builder
public class Comment {

    private Reservation reservation;
    private User submitter;
    private int star;
    private String comment;

}
