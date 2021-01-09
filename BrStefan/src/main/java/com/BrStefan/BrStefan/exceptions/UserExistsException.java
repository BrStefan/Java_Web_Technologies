package com.BrStefan.BrStefan.exceptions;

import org.springframework.http.ResponseEntity;

public class UserExistsException extends RuntimeException{

    public UserExistsException(String username){
        super(String.format("A user with username %s already exists!", username));
    }
}
