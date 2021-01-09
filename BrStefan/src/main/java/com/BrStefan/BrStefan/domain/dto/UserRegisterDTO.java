package com.BrStefan.BrStefan.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    @NotNull(message = "Field required")
    private String username;

    @NotNull(message = "Field required")
    @Size(min = 4, message = "Password must have at least 4 characters")
    private String pass;

    @NotNull(message = "Field required")
    private String full_name;

}
