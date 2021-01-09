package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.RankUpDTO;
import com.BrStefan.BrStefan.domain.dto.UserLoginDTO;
import com.BrStefan.BrStefan.domain.dto.UserRegisterDTO;
import com.BrStefan.BrStefan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(UserRegisterDTO user){
        return userRepository.register(user);
    }

    public User login(UserLoginDTO user){
        return userRepository.login(user);
    }

//    public List<User> getAll(){
//        return userRepository.getAll();
//    }
//
//    public User delete(User user){
//        return userRepository.delete(user);
//    }

    public void rankUp(RankUpDTO rankup){
        userRepository.rankUp(rankup);
    }
}
