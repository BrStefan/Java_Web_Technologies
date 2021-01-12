package com.BrStefan.BrStefan.exceptions;

import javax.management.Notification;

public class NotificationException extends RuntimeException{
    public NotificationException(String message){
        super(message);
    }
}
