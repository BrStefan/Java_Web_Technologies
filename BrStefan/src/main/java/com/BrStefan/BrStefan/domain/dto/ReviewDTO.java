package com.BrStefan.BrStefan.domain.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    @NotNull
    private int user_id;

    @NotNull
    private int reservation_id;

    @NotNull
    @Min(1)
    @Max(5)
    private int stars;

    private String review;
}
