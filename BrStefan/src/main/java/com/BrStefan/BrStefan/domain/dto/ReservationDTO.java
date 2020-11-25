package com.BrStefan.BrStefan.domain.dto;

import com.BrStefan.BrStefan.domain.Table;
import com.BrStefan.BrStefan.domain.User;
import lombok.*;

import java.util.Date;

@Getter
@Builder
public class ReservationDTO {

    private User owner;
    private Date date;
    private boolean confirmed;
}
