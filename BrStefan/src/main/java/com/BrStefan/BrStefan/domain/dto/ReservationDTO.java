package com.BrStefan.BrStefan.domain.dto;

import com.BrStefan.BrStefan.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    @NotNull
    private String owner;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date reservation_date;

    @NotNull
    @Min(1)
    @Max(5)
    private int number_of_guests;

    @NotNull
    @Min(1)
    @Max(19)
    private int table;
}
