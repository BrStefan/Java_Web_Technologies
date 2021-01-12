package com.BrStefan.BrStefan.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroupDTO {
    @NotNull
    private int user_id;

    @NotNull
    private String name;
}
