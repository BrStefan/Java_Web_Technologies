package com.BrStefan.BrStefan.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    @NotNull
    private int owner;

    @NotNull
    private int group;

    @NotNull
    private String message;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;
}
