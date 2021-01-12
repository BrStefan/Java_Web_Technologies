package com.BrStefan.BrStefan.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserGroupDTO {
    @NonNull
    private int user_id;

    @NotNull
    private int customer_id;

    @NotNull
    private int group_id;
}
