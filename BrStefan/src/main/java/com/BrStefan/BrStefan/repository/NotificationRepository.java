package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.Notification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepository {

    private List<Notification> notifications;

    private Notification add(Notification notification){
        notifications.add(notification);
        return notification;
    }

    private List<Notification> getNotifications(){
        return notifications;
    }

    private Notification delete(Notification notification){
        notifications.remove(notification);
        return notification;
    }
}
