package com.BrStefan.BrStefan.controller;

import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.RankUpDTO;
import com.BrStefan.BrStefan.domain.dto.UserLoginDTO;
import com.BrStefan.BrStefan.domain.dto.UserRegisterDTO;
import com.BrStefan.BrStefan.exceptions.LoginException;
import com.BrStefan.BrStefan.exceptions.UserExistsException;
import com.BrStefan.BrStefan.exceptions.UserRoleException;
import com.BrStefan.BrStefan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object>register(@RequestBody @Valid UserRegisterDTO user){
        try {
            User user_response = userService.register(user);
            return ResponseEntity.ok().body(user_response);
        }
        catch (UserExistsException e) {
            return ResponseEntity.status(403).body("Username already taken");
        }
    }

    @PostMapping("/rankUp")
    public ResponseEntity<Object> rankUp(@RequestBody @Valid RankUpDTO rankup){
        try{
            userService.rankUp(rankup);
            return ResponseEntity.ok().body("User have been promoted");
        }
        catch(UserRoleException e){
            return ResponseEntity.status(401).body("You must be a staff member in order to rankUp a member!");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Object>login(@RequestBody @Valid UserLoginDTO user) {
        try {
            User user_response = userService.login(user);
            return ResponseEntity.ok().body(user_response);
        } catch (LoginException e) {
            return ResponseEntity.status(401).body("Invalid Credentials!");
        }
    }
}
