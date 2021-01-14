package com.BrStefan.BrStefan.domain;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup {
    private int id;
    private int user_id;
    private int group_id;
}
