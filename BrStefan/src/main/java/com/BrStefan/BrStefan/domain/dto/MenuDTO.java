package com.BrStefan.BrStefan.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    @NotNull
    private int owner_id;

    @NotNull
    private String name;

    @NotNull
    private String description;
}
