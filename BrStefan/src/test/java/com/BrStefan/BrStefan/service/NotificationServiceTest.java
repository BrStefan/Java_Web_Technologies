package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.Notification;
import com.BrStefan.BrStefan.domain.Reservation;
import com.BrStefan.BrStefan.domain.Review;
import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.*;
import com.BrStefan.BrStefan.repository.NotificationRepository;
import com.BrStefan.BrStefan.repository.ReservationRepository;
import com.BrStefan.BrStefan.repository.ReviewRepository;
import com.BrStefan.BrStefan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;


    @Test
    void testBook(){
        NotificationDTO notificationDTO = new NotificationDTO(1,1,"Message",new Date(2020, Calendar.FEBRUARY,12));
        Notification notification = new Notification(1,1,1,"Message",new Date(2020, Calendar.FEBRUARY,12));

        notificationService.add(notificationDTO);

        assertThat(notification.getMessage()).isEqualTo(notificationDTO.getMessage());

    }

    @Test
    void testList(){
        List<Notification> notificationList = new ArrayList<>();
        notificationList.add(
                new Notification(1,1,1,"Message",new Date(2020, Calendar.FEBRUARY,12))
        );
        notificationList.add(
                new Notification(2,1,2,"Message1",new Date(2020, Calendar.FEBRUARY,12))
        );
        notificationList.add(
                new Notification(3,2,2,"Message2",new Date(2020, Calendar.FEBRUARY,12))
        );

        List<Notification> res = new ArrayList<>();
        res.add(
                new Notification(1,1,1,"Message",new Date(2020, Calendar.FEBRUARY,12))
        );
        res.add(
                new Notification(2,1,2,"Message1",new Date(2020, Calendar.FEBRUARY,12))
        );

        when(notificationService.All()).thenReturn(res);

        assertThat(notificationList.get(0).getMessage()).isEqualTo(res.get(0).getMessage());
        assertThat(notificationList.get(1).getMessage()).isEqualTo(res.get(1).getMessage());
    }

}