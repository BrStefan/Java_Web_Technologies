package com.BrStefan.BrStefan.domain;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class Menu {

    private String name;
    private List<Food> products;
}
