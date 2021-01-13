package com.BrStefan.BrStefan.domain;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class Menu {

    private int id;
    private String name;
    private String description;
}
