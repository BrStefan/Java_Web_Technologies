package com.BrStefan.BrStefan.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {

    private String username;
    private String full_name;
}
