package com.BrStefan.BrStefan.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @NotNull(message = "Field required")
    private String username;

    @NotNull(message = "Field required")
    private String pass;
}
