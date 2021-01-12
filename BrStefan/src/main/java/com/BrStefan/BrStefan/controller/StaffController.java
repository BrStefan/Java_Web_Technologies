package com.BrStefan.BrStefan.controller;


import com.BrStefan.BrStefan.domain.Notification;
import com.BrStefan.BrStefan.domain.Reservation;
import com.BrStefan.BrStefan.domain.dto.AddUserGroupDTO;
import com.BrStefan.BrStefan.domain.dto.CreateGroupDTO;
import com.BrStefan.BrStefan.domain.dto.NotificationDTO;
import com.BrStefan.BrStefan.exceptions.AddMemberGroupException;
import com.BrStefan.BrStefan.exceptions.CreateGroupException;
import com.BrStefan.BrStefan.exceptions.NotificationException;
import com.BrStefan.BrStefan.exceptions.StaffException;
import com.BrStefan.BrStefan.service.GroupService;
import com.BrStefan.BrStefan.service.NotificationService;
import com.BrStefan.BrStefan.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/reservations")
    public ResponseEntity<Object>getReservations(@RequestBody int user_id){
        try{
            List<Reservation> reservations = reservationService.getReservations(user_id);
            return ResponseEntity.ok().body(reservations);
        }
        catch(StaffException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/create_group")
    public ResponseEntity<Object>createGroup(@RequestBody @Valid CreateGroupDTO createGroupDTO){
        try{
            groupService.createGroup(createGroupDTO);
            return ResponseEntity.ok().body("Group created");
        }
        catch (CreateGroupException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/add_member_to_group")
    public ResponseEntity<Object>addMemberToGroup(@RequestBody @Valid AddUserGroupDTO addUserGroupDTO){
        try{
            groupService.addToGroup(addUserGroupDTO);
            return ResponseEntity.ok().body("User added to group!");
        }catch(AddMemberGroupException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/add_notification")
    public ResponseEntity<Object>addNotification(@RequestBody @Valid NotificationDTO notificationDTO){
        try{
            notificationService.add(notificationDTO);
            return ResponseEntity.ok().body("Notification added");
        }catch (NotificationException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/notifications")
    public ResponseEntity<Object>getAll(){
        try{
            List<Notification> notifications = notificationService.All();
            return ResponseEntity.ok().body(notifications);
        }catch (NotificationException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
