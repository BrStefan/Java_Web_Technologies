package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.Notification;
import com.BrStefan.BrStefan.domain.dto.NotificationDTO;
import com.BrStefan.BrStefan.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void add(NotificationDTO notificationDTO){
        notificationRepository.add(notificationDTO);
    }

    public List<Notification> All(){
        return notificationRepository.getAll();
    }
}
