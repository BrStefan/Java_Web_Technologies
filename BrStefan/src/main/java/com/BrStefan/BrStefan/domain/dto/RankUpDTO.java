package com.BrStefan.BrStefan.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RankUpDTO {

    @NotNull
    private String user_id;

    @NotNull
    private String target_id;
}
