package com.BrStefan.BrStefan.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    private int Id;
    private int owner;
    private int group;
    private String message;
    private Date date;
}
