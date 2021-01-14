package com.BrStefan.BrStefan.domain;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    private int id;
    private String name;
    private String description;
}
