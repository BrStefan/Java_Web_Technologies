package com.BrStefan.BrStefan.exceptions;

public class LoginException extends RuntimeException{
    public LoginException(){
        super("Invalid Credentials!");
    }
}
