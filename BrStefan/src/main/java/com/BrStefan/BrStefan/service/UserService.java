package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user){
        return userRepository.register(user);
    }

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public User delete(User user){
        return userRepository.delete(user);
    }

    public User rankUp(User user){
        return userRepository.rankUp(user);
    }
}
