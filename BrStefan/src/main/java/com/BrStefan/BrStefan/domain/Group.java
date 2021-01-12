package com.BrStefan.BrStefan.domain;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private int id;
    private int user_id;
    private String name;
}
