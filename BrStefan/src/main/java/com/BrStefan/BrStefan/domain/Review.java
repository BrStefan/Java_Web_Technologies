package com.BrStefan.BrStefan.domain;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private int id;
    private int user_id;
    private int reservation_id;
    private int stars;
    private String review;
}
