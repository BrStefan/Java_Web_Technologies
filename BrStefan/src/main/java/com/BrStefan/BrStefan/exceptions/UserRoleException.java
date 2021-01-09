package com.BrStefan.BrStefan.exceptions;

public class UserRoleException extends RuntimeException{
    public UserRoleException(){
        super("Bad role!");
    }
}
