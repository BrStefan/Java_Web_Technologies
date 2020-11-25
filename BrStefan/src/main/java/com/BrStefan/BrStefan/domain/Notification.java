package com.BrStefan.BrStefan.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
public class Notification {

    private String name;
    private String description;
    private List<User> users;
    private Date date;
    private boolean sent;
}
