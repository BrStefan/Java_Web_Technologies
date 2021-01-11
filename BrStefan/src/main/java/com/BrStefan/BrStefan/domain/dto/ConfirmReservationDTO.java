package com.BrStefan.BrStefan.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmReservationDTO {

    @NotNull
    private String owner;

    @NotNull
    private String reservation_id;
}
