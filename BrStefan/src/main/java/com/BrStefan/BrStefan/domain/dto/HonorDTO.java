package com.BrStefan.BrStefan.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HonorDTO {
    @NotNull
    private String user_id;

    @NotNull
    private String reservation_id;
}
